(ns pistole.C002-test
  (:require [clojure.test :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [pistole.C002 :refer [write-field]]))

(def uformatter (eclp.UFormatter/getInstance))

(defn eclp-format [spec obj]
  (.pack uformatter (into-array String spec) obj))

(def date-format-prop
  (prop/for-all [f (gen/elements ["T" "T2" "T3" "T4" "T5"
                                  "D" "RD" "RD1" "RD2" "RD3" "SND"])]
    (let [d (java.util.Date.)
          f1 [f "14" "O" "1" "Test"]
          f2 [(keyword f) 14 1 "Test"]]
      (= (eclp-format f1 d) (write-field f2 d)))))

(comment

  (clojure.core/require 'pistole.C002-test)
  (require '[clojure.test.check :as tc])
  (tc/quick-check 50 date-format-prop)

  (defn unit-test [f d]
    (let [f1 [f "14" "O" "1" "Test"]
          f2 [(keyword f) 14 1 "Test"]]
      (println "eclp: " (eclp-format f1 d))
      (println "COO2: " (write-field f2 d))
      (= (eclp-format f1 d) (write-field f2 d))))

  (unit-test "SND" (java.util.Date.))
  (unit-test "SND" nil)

  (def fmt eclp-format)

  (fmt ["A" "6" "O" "1" "MsgToken"] "ABC")

  (fmt ["AN" "6" "O" "1" "MsgToken"] "ABC")
  (fmt ["AN" "6" "O" "1" "MsgToken"] 123M)
  (write-field [:AN 6 1 "MsgToken"] "ABC")
  (write-field [:AN 6 1 "MsgToken"] 123M)

  (fmt ["N" "6" "O" "1" "MsgToken"] 1234M)
  (fmt ["N" "6.2" "O" "1" "MsgToken"] 12.34M)
  (fmt ["N" "6.2" "O" "1" "MsgToken"] 100.236M)
  (fmt ["N" "6.2" "O" "1" "MsgToken"] nil)

  ;; not supported by fmt
  (fmt ["N" "6.2" "O" "3" "MsgToken"] [100.236M 123.12M 20.23M])
  (fmt ["N" "6.2" "O" "1" "MsgToken"] -100.236M)

  (write-field [:N [6 2] 1 "MsgToken"] 12.34M)
  (write-field [:N [6 2] 3 "MsgToken"] [100.236M 123.12M 20.23M])

  (fmt ["SN" "6.2" "O" "1" "MsgToken"] 100.236M)
  (fmt ["SN" "6.2" "O" "1" "MsgToken"] -100.236M)

  (fmt ["T","14","O", "1", "RqstTimeStampBefore"] nil)
  (fmt ["T","14","O", "1", "RqstTimeStampBefore"] (java.util.Date.))

  (fmt ["SND","9","O", "1", "RqstTimeStampBefore"] (java.util.Date.))
  (fmt ["SND","9","O", "1", "RqstTimeStampBefore"] nil)

  (fmt ["AN", "5", "R", "1", "BlockMsgNo"] "123")
  (fmt ["AN", "5", "R", "1", "BlockMsgNo"] "")
  (fmt ["AN", "5", "R", "1", "BlockMsgNo"] nil)

  (fmt ["B", "10", "M", "1", "MoreRecordsInd"] false)

  ;; PD format doesn't seem to work.
  (fmt ["PD" "6" "O" "1" "MsgToken"] 1234)

  (def eclp-formatter2 (eclp.UFormatter/getInstance 2r1100 2r1101))
  (defn fmt2 [spec obj]
    (.pack eclp-formatter2 (into-array String spec) obj))

  ;; PD still does not work
  (fmt2 ["PD" "6" "O" "1" "MsgToken"] 1234)

  (.toBigInteger (BigDecimal. "234129223372036854775808"))
  (format "%030d" (.toBigInteger (BigDecimal. "234129223372036854775808")))

  )