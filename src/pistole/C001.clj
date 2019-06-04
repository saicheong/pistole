(ns pistole.C001
  (:refer-clojure :exclude [read-string])
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))


(defn- size-spec [^String s]
  {:post [(if (vector? %) (> (first %) (second %)) (integer? %))]}
  (if (str/index-of s \.)
    (mapv #(Integer/parseInt %) (str/split s #"\."))
    (Integer/parseInt s)))

(defn- field-spec
  "Parse the string and position-based object serialization spec"
  [[tag size _ rep label]]
  [(keyword tag)
   (size-spec size)
   (Integer/parseInt rep)
   label])

(defn- clj-structure
  "Parse the java-syntax string and position-based serialization specs"
  [^String s]
  (as-> s %
        (re-find #"(?s)\{.*\}" %)
        (str/replace % \{ \[)
        (str/replace % \} \])
        (edn/read-string %)
        (map field-spec %)))


(comment

  (clojure.core/require 'pistole.C001)

  (size-spec "15.3")
  (size-spec "15")
  (size-spec "15.30")

  (field-spec ["A" "10" "O" "1" "MsgToken"])
  (field-spec ["A" "10.4" "O" "1" "MsgToken"])
  (field-spec ["AN" "10" "O" "1" "MsgToken"])

  ;; regex to extract contents within braces
  (re-find #"(?s)\{.*\}" "as= {{abc}}")
  (re-find #"(?s)\{.*\}" "as= {{abc,abc, {a b c}}};//ar")

  (->> (slurp "resources/C001-test.txt")
       clj-structure)

  (->> (slurp "resources/C001-test2.txt")
       clj-structure)

  (->> (slurp "resources/C001-test3.txt")
       clj-structure)

===)
