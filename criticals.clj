#!/bin/env bb

(def full-glossary-text (slurp "cloudguruawsarchitect.txt"))
(def full-exam-text (slurp "exam.txt"))
(defn match-line-from-glossary [line] (re-seq (re-pattern (str "(?i)(.*)" line "(.*)")) full-glossary-text))
(defn print-critical-concepts []
  (as-> full-exam-text f
  (re-seq #" def .*" f)
  (filter (fn [x] (not= nil x)) f)
  (map match-line-from-glossary f)
  (map println f)))

(print-critical-concepts)
