#!/bin/env bb

(def full-glossary-text (slurp "cloudguruawsarchitect.txt"))
(def full-exam-text (slurp "exam.txt"))
(defn match-from-glossary [line] (re-matches line full-glossary-text))
(defn critical-concepts []
  (as-> full-exam-text f
  (re-seq #"def.*" f)
  (filter (not= nil) f)
  (map match-from-glossary f)))

(critical-concepts)
