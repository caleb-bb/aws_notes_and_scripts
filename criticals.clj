#!/bin/env bb

(def full-glossary-text (slurp "cloudguruawsarchitect.txt"))
(def full-exam-text (slurp "exam.txt"))
(defn insert-line-break [line-from-file] (str line-from-file "\n"))
(defn match-line-from-glossary [line] (re-find (re-pattern (str "(?i)(.*)" line "(.*)")) full-glossary-text))
(defn print-critical-concepts []
  (as-> full-exam-text f
  (re-seq #" def .*" f)
  (filter (fn [x] (not= nil x)) f)
  (map match-line-from-glossary f)
  (map insert-line-break f)
  (reduce str "" f)
  (print f)))

(print-critical-concepts)
;; (spit "criticals.txt" (print-critical-concepts))
