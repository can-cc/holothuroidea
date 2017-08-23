(require '[cljs.build.api :as b])

(println "Building ...")

(let [start (System/nanoTime)]
  (b/build "src"
           {:main 'holothuroidea.core
            :asset-path "out"
            :output-to "out/holothuroidea.js"
            :output-dir "out"
            :optimizations :simple
            :target :nodejs
            :verbose true
            :cache-analysis true
            :pretty-print true})
  (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds"))
