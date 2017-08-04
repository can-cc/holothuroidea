(require '[cljs.build.api :as b])

(println "Building ...")

(let [start (System/nanoTime)]
  (b/build "src"
    {:main 'holothuroidea.core
     :output-to "out/holothuroidea.js"
     :output-dir "out"
     :target :nodejs
     :pretty-print true
     :verbose true})
  (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds"))


