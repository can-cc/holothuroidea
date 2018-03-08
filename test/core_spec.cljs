(ns test.core
  (:require [cljs.test :as test :refer-macros [deftest is testing run-tests]]
            [cljs.nodejs :as nodejs]
            [test.build]))

(nodejs/enable-util-print!)

(cljs.test/run-tests)
(cljs.test/run-tests 'test.build)
