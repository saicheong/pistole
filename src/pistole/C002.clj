(ns pistole.C002
  "A library for working with fixed-length data in the same way as
   UFormatter.

   The aim is for this to be a direct replacement of UFormatter"

  (:refer-clojure :exclude [read-string])
  (:require [clojure.string :as str])
  (:import (java.util Date Calendar)
           (java.text SimpleDateFormat)
           (java.math BigDecimal)))

(comment "=== Notes ==="

  "Objective: A library for working with fixed-length data..."

  "This is a port of UFormatter although UFormatter could also
   be used directly - it has dependencies into other codes that
   is not desirable

   Changes from UFormatter:
   :N - add validation against negative inputs
   :N - add support for repetitions

   "

  "Future:
   * adding functions for fixed-length file processing"


  "          ******    ")

(defn- ^String fill [^Character c cnt]
  (apply str (repeat cnt c)))

(defn- ^String left-pad [^String s pad sz]
  {:pre [(>= sz (count s))]}
  (str (apply str (repeat (- sz (count s)) pad))
       s))

(defn- ^String left-trim [^CharSequence s c]
  (let [len (.length s)]
    (loop [index 0]
      (if (= len index)
        ""
        (if (= c (.charAt s index))
          (recur (unchecked-inc index))
          (.. s (subSequence index len) toString))))))

(defn partition-string [^CharSequence s len]
  (if (seq s)
    (lazy-seq (cons (subs s 0 len) (partition-string (subs s len) len)))))


(defmulti write-field-1 (fn [spec _] (first spec)))
(defmulti read-field-1 (fn [spec _] (first spec)))

;; left aligned string
(defmethod write-field-1 :A
  [[_ sz lbl] data]
  (assert (>= sz (count data)) (str "Error writing " lbl) )
  (let [fmt (str "%-" sz "s")
        tgt (if data data "")]
    (format fmt tgt)))

(defmethod read-field-1 :A
  [[_ sz] ^String obj]
  (str/trim (subs obj 0 sz)))

(defmethod write-field-1 :AN
  [[_ sz lbl] s]
  (assert (>= sz (count s)) (str "Error writing " lbl) )
  (left-pad s "0" sz))

(defmethod read-field-1 :AN
  [[_ sz] ^String obj]
  (left-trim (subs obj 0 sz) \0))

(defn- to-string [sc ^BigDecimal obj]
  (if obj
    (-> obj
        (.setScale sc BigDecimal/ROUND_HALF_UP)
        (.movePointRight sc)
        (.toBigInteger)
        (.toString))
    "0"))

(defn- to-number [sc ^String s]
  (let [n (BigDecimal. s)]
    (if (> sc 0) (.movePointLeft n sc) n)))

(defmethod write-field-1 :N
  [[_ sz] ^BigDecimal obj]
  {:pre [(>= obj 0)]}
  (let [[pr sc] (if (vector? sz) sz [sz 0])
        n (to-string sc obj)]
    (left-pad n \0 pr)))

(defmethod read-field-1 :N
  [[_ sz] ^String s]
  (let [[_ sc] (if (vector? sz) sz [sz 0])]
    (to-number sc s)))

(defmethod write-field-1 :SN
  [[_ sz] ^BigDecimal obj]
  (let [[pr sc] (if (vector? sz) sz [sz 0])
        n (to-string sc (.abs obj))]
    (str (if (>= obj 0) \+ \-)
         (left-pad n \0 (dec pr)))))

(defmethod read-field-1 :SN
  [[_ sz] ^String s]
  (let [[_ sc] (if (vector? sz) sz [sz 0])
        n (to-number sc (subs s 1))]
    (if (= (.charAt s 0) \-) (* -1 n) n)))

(def RD3-fmt "yyyyDDD")

(defmethod write-field-1 :SND
  [[_ sz] ^Date d]
  {:pre [(> sz 7)]}
  (let [sf (if d (.format (SimpleDateFormat. RD3-fmt) d) "")]
    (str "+" (left-pad sf \0 (dec sz)))))

(defmethod read-field-1 :SND
  [[_ sz] ^String s]
  (let [nil-rep (str "+" (left-pad "" \0 (dec sz)))]
    (if (not= nil-rep s)
      (.parse (SimpleDateFormat. RD3-fmt) (subs s (- sz 7))))))

;; Note: date-spec does not support :SND
(defmacro date-spec [tag fmt-str]
  `(let [nil-repr# (fill \0 (count ~fmt-str))]
     (do (defmethod write-field-1 ~tag
           [_# ^Date d#]
           (if d#
             (.format (SimpleDateFormat. ~fmt-str) d#)
             nil-repr#))
         (defmethod read-field-1 ~tag
           [_# ^String s#]
           (if (not= nil-repr# s#)
             (.parse (SimpleDateFormat. ~fmt-str) s#))))))

(date-spec :D    "ddMMyyyy")
(date-spec :T    "yyyyMMddHHmmss")
(date-spec :T2    "yyyyDDDHHmmss")
(date-spec :T3   "ddMMyyyyHHmmss")
(date-spec :T4         "HH:mm:ss")
(date-spec :T5           "HHmmss")
(date-spec :RD   "yyyyMMdd")
(date-spec :RD1    "ddMMyy")
(date-spec :RD2  "yyyy-MM-dd")
(date-spec :RD3  RD3-fmt)

(defmethod write-field-1 :B
  [[_ sz] ^Boolean b]
  {:pre [(= 1 sz)]}
  (if b "Y" "N"))

(defmethod read-field-1 :B
  [[_ sz] ^String s]
  {:pre [(= 1 sz)]}
  (if (= "Y" (subs s 0 1)) true false))


(defn write-field
  "Serializes obj according to layout spec"
  [[tag sz rep label] obj]
  (let [fs [tag sz label]]
    (if (and (> rep 1) (coll? obj))
      (if (= rep (count obj))
        (apply str (map #(write-field-1 fs %) obj))
        (ex-info "number != count" {:number rep :count (count obj)}))
      (write-field-1 fs obj))))

(defn read-field
  "De-serializes string according to layout specs"
  [[tag sz rep label] s]
  (let [fs [tag sz label]
        len (if (vector? sz) (first sz) sz)]
    (if (> rep 1)
      (let [rlen (* rep len)
            rs (subs s 0 rlen)]
        {:read [label (mapv read-field-1 (repeat fs) (partition-string rs len))]
         :rest (subs s rlen)})
      {:read [label (read-field-1 fs (subs s 0 len))]
       :rest (subs s len)})
    ))

(defn write-data [specs coll]
  {:pre [(= (count specs) (count coll))]}
  (apply str (map write-field specs coll)))

(defn read-string [specs s]
  (letfn [(rf [[p s] x]
            (let [{:keys [read rest]} (read-field x s)]
              [(conj p read) rest]))]
    (reduce rf [[] s] specs)))


(comment

  (clojure.core/require 'pistole.C002 :reload)

  ; string
  (format "%6s" "abcd")


  ; number
  (format "%5d" 123)

  ; floats
  (format "%5.2f" 1.23)
  (format "%5.2f" 1.23M)
  (format "%5f" 123M)

  ; floats - rounding
  (format "%5.2f" 1.235)
  (format "%5.2f" 1.234)

  ; number padding
  (format "%05d" 123)
  (format "%05d" 123)
  (format "%05.2f" 1.23)

  ;; left justified
  (format "%-6s" "abcd")
  (format "%-5d" 123)

  ;; date/time formatting and parsing
  (import (java.util Date Calendar))

  ;; create the test date (exclude milliseconds - as is not used in formatting
  (def test-date (.getTime (doto (Calendar/getInstance)
                             #_(.set Calendar/SECOND 0)
                             (.set Calendar/MILLISECOND 0))))

  ;; formatting date using format is tedious - you have a long formatting string
  (format "%1$tY-%1$tm-%1$td-%1$tH-%1$tM-%1$tS" test-date)
  (format "%1$tY%1$tm%1$td%1$tH%1$tM%1$tS" test-date)
  (format "%-10tY" test-date)
  (format "%-10tR" test-date)

  ;; Using the convenient but not thread-safe SimpleDateFormat
  (import '(java.text SimpleDateFormat))

  ;; What is the timezone used?
  (def date-format (SimpleDateFormat. "yyyyMMddHHmmss"))
  (.format date-format test-date)
  (.parse date-format *1)
  (= *1 test-date)
  (.format date-format nil)


  ;; Or use java.time (a core library in java 8)
  (import '(java.time.format DateTimeFormatter))
  (import '(java.time Instant LocalDateTime ZoneId))

  ;; getting a java.time instant of java.util.Date
  (def jt-test-date (.toInstant test-date))

  ;; a java.util.Date must be converted to a java.time instant,
  ;; attached to a time zone, then formatted
  (def jt-date-format (DateTimeFormatter/ofPattern "yyyyMMddHHmmss"))
  (def jt-tz-test-date (-> test-date
                           (.toInstant)
                           (.atZone (ZoneId/systemDefault))))
  ;; format time-zoned date
  (.format jt-tz-test-date jt-date-format)

  ;; format with formatter
  (.format jt-date-format jt-tz-test-date)

  ;; using a time-zoned formatter
  (.format (.withZone jt-date-format (ZoneId/systemDefault)) jt-test-date)

  ;; test format-parse date = original date using time-zoned formatter
  (let [fmt (.withZone jt-date-format (ZoneId/systemDefault))]
    (as-> jt-test-date %
          (.format fmt %)
          (.parse fmt %)
          (Instant/from %)
          (= jt-test-date %)))


  "  === UNIT TESTS ==="
  (write-field-1 [:A 5 "Test"] "")
  (write-field-1 [:A 5 "Test"] "ABC")
  (write-field-1 [:A 5 "Test"] nil)
  (write-field-1 [:A 5 "Test"] "ABCDE")
  (write-field-1 [:A 5 "Test"] "ABCDEF")
  (write-field [:A 10 1 "MsgToken"] "ABC")
  (write-field [:A 10 3 "MsgToken"] ["ABC" "ABCDE" "ABCDEF"])

  (write-field-1 [:T 0 "Test"] test-date)
  (read-field-1 [:T 0 "Test"] *1)

  (write-field-1 [:T3 0 "Test"] test-date)
  (read-field-1 [:T3 0 "Test"] *1)

  (write-field-1 [:D 0 "Test"] test-date)
  (read-field-1 [:D 0 "Test"] *1)

  (write-field-1 [:T 0 "Test"] nil)
  (write-field-1 [:T3 0 "Test"] nil)
  (write-field-1 [:D 0 "Test"] nil)

  (write-field-1 [:N 6 "Test"] 1234M)
  (read-field-1 [:N 6 "Test"] *1)
  (write-field-1 [:N [6 2] "test"] 100.236M)
  (read-field-1 [:N [6 2] "Test"] *1)
  (write-field-1 [:N [6 2] "test"] -100.236M)

  (write-field-1 [:SN 6 "Test"] 1234M)
  (read-field-1 [:SN 6 "Test"] *1)
  (write-field-1 [:SN 6 "Test"] -123M)
  (read-field-1 [:SN 6 "Test"] *1)
  (write-field-1 [:SN [6 1] "Test"] 123.4M)
  (read-field-1 [:SN [6 1] "Test"] *1)
  (write-field-1 [:SN [6 1] "Test"] -12.3M)
  (read-field-1 [:SN [6 1] "Test"] *1)

  (write-field-1 [:N 6 "Test"] nil)
  (write-field-1 [:N [6 2] "test"] nil)

  (write-field-1 [:AN 6 "Test"] "123")
  (write-field-1 [:AN 6 "Test"] "")
  (write-field-1 [:AN 6 "Test"] nil)

  (write-field-1 [:B 1 "Test"] true)
  (write-field-1 [:B 1 "Test"] false)
  (read-field-1 [:B 1 "Test"] *1)

  (write-field-1 [:B 1 "Test"] nil)
  (write-field-1 [:B 2 "Test"] true)

  (BigDecimal. "0010024")

  ;; parsing
  (def test-date (.getTime (doto (Calendar/getInstance)
                             #_(.set Calendar/SECOND 0)
                             (.set Calendar/MILLISECOND 0))))

  (def spec [[:A 5 1 "Field1"]
             [:A 10 1 "Field2"]
             [:T 14 1 "Date1"]
             [:T3 14 1 "Date2"]
             [:AN 5 1 "NumericString"]
             [:B 1 1 "Boolean1"]])

  (write-data spec ["A" "B" test-date test-date "123" true])
  (read-string spec *1)



  ===)
