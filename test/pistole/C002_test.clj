(ns pistole.C002-test
  (:require [clojure.test :refer :all]))

(def uformatter (eclp.UFormatter/getInstance))

(defn eclp-format [spec obj]
  (.pack uformatter (into-array String spec) obj))

(comment

  (clojure.core/require 'pistole.C002-test)
  (def fmt eclp-format)

  (fmt ["A" "6" "O" "1" "MsgToken"] "ABC")
  (fmt ["N" "6" "O" "1" "MsgToken"] 1234M)
  (fmt ["N" "6.2" "O" "1" "MsgToken"] 100.236M)
  (fmt ["N" "6.2" "O" "1" "MsgToken"] nil)

  (fmt ["T","14","O", "1", "RqstTimeStampBefore"] nil)
  (fmt ["T","14","O", "1", "RqstTimeStampBefore"] (java.util.Date.))

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