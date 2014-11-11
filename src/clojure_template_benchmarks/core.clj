(ns clojure-template-benchmarks.core
  (:use criterium.core
        tinsel.core
        clojure-csv.core)
  (:require [clabango.parser :refer [render render-file]]
            [selmer.parser :as selmer]
            [stencil.core :as stencil]
            [hiccup.core :as hiccup]
            [me.raynes.laser :as laser :refer [defdocument]]
            [net.cgrand.enlive-html :as enlive]
            [me.shenfeng.mustache :as mustache]
            [clj-pebble.core :as pebble]))

(def bar (str "bar"))


(defn simple-str []
  (str "<span class=\"foo\">"
       bar
       "</span>"))

(defn list-str [ceil]
  (str "<ul>"
       (for [n (range 1 ceil)]
         (str "<li>" n "</li>"))
       "</ul>"))


(defn simple-hiccup []
  (hiccup/html [:span {:class "foo"} bar]))

(defn list-hiccup [ceil]
  (hiccup/html [:ul (for [x (range 1 ceil)]
                      [:li x])]))

(defn simple-hiccup-hint []
  (hiccup/html [:span {:class "foo"} ^String bar]))

(defn list-hiccup-hint [ceil]
  (hiccup/html [:ul (for [x (range 1 ceil)]
                      [:li ^Number x])]))


(defn simple-clabango-no-fd []
  (render "<span class=\"foo\">{{bar}}</span>" {:bar bar}))

(defn list-clabango-no-fd [ceil]
  (render "<ul>{% for item in items %}<li>{{item}}</li>{% endfor %}</ul>" {:items (range 1 ceil)}))


(defn simple-clabango []
  (render-file "clojure_template_benchmarks/templates/simple.html" {:bar bar}))

(defn list-clabango [ceil]
  (render-file "clojure_template_benchmarks/templates/list.html" {:items (range 1 ceil)}))


(defn simple-selmer []
  (selmer/render-file "clojure_template_benchmarks/templates/simple.html" {:bar bar}))

(defn list-selmer [ceil]
  (selmer/render-file "clojure_template_benchmarks/templates/list.html" {:items (range 1 ceil)}))


(defn simple-selmer-no-fd []
  (selmer/render "<span class=\"foo\">{{bar}}</span>" {:bar bar}))

(defn list-selmer-no-fd [ceil]
  (selmer/render "<ul>{% for item in items %}<li>{{item}}</li>{% endfor %}</ul>" {:items (range 1 ceil)}))


(defn simple-pebble []
  (pebble/render-file "src/clojure_template_benchmarks/templates/simple.html" {:bar bar}))

(defn list-pebble [ceil]
  (pebble/render-file "src/clojure_template_benchmarks/templates/list.html" {:items (range 1 ceil)}))


(defn simple-pebble-no-fd []
  (pebble/render "<span class=\"foo\">{{bar}}</span>" {:bar bar}))

(defn list-pebble-no-fd [ceil]
  (pebble/render "<ul>{% for item in items %}<li>{{item}}</li>{% endfor %}</ul>" {:items (range 1 ceil)}))


(defn simple-stencil-no-fd []
  (stencil/render-string "<span class=\"foo\">{{bar}}</span>" {:bar bar}))

(defn list-stencil-no-fd [ceil]
  (stencil/render-string "<ul>{{#items}}<li>{{.}}</li>{{/items}}</ul>" {:items (range 1 ceil)}))


(defn simple-stencil []
  (stencil/render-file "clojure_template_benchmarks/templates/simple.mustache" {:bar bar}))

(defn list-stencil [ceil]
  (stencil/render-file "clojure_template_benchmarks/templates/list.mustache" {:items (range 1 ceil)}))

(mustache/deftemplate simple-mst (slurp "src/clojure_template_benchmarks/templates/simple.mustache"))
(mustache/deftemplate list-mst (slurp "src/clojure_template_benchmarks/templates/list.mustache"))

(defn simple-mustache []
  (simple-mst {:bar bar}))

(defn list-mustache [ceil]
  (list-mst {:items (range 1 ceil)}))

(deftemplate simple-tinsel [[:span {:class "foo"}]] [] (has-class? "foo") (set-content bar))
(deftemplate list-tinsel [[:ul]] [ceil] (tag= :ul) (set-content (for [x (range 1 ceil)] [:li x])))

(defdocument simple-laser "<span class=\"foo\"></span>" []
             (laser/class= "foo") (laser/content bar))
(defdocument list-laser "<ul></ul>" [ceil]
             (laser/element= :ul) (laser/content
                                    (for [x (range 1 ceil)]
                                      (laser/node :li :content (str x)))))

#_(defdocument simple-laser-hinted "<span class=\"foo\"></span>" []
             (laser/class= "foo") (laser/content ^String bar))
#_(defdocument list-laser-hinted "<ul></ul>" [ceil]
             (laser/element= :ul) (laser/content
                                    (for [x (range 1 ceil)]
                                      (laser/node :li :content (str ^Number x)))))

(enlive/deftemplate simple-enlive-core "clojure_template_benchmarks/templates/simple.enlive" []
                    [:span.foo] (enlive/content bar))
(enlive/deftemplate list-enlive-core "clojure_template_benchmarks/templates/list.enlive" [ceil]
                    [:ul] (enlive/clone-for [x (range 1 ceil)]
                                            (enlive/content (str x))))

(defn simple-enlive [] (apply str (simple-enlive-core)))
(defn list-enlive [ceil] (apply str (list-enlive-core ceil)))

(defmacro do-benchmarks
  [benchmark-name simple-expr small-list-expr big-list-expr]
  `(do
     (println "\n **** Running Benchmark:" ~benchmark-name "*** \n")
     (-> {}
         (assoc
           :simple
           (do
             (println ">>> Simple Data Injection\n")
             (quick-benchmark ~simple-expr {})))
         (assoc
           :small-list
           (do
             (println ">>> Small List (50 items)\n")
             (quick-benchmark ~small-list-expr {})))
         (assoc
           :big-list
           (do
             (println ">>> Big List (1000 items)\n")
             (quick-benchmark ~big-list-expr {})))
         (assoc :name ~benchmark-name))))

(defn str-benches []
  (do-benchmarks
    "String"
    (simple-str)
    (list-str 50)
    (list-str 1000)))

(defn hiccup-benches []
  (do-benchmarks
    "Hiccup"
    (simple-hiccup)
    (list-hiccup 50)
    (list-hiccup 1000)))

(defn hiccup-benches-hinted []
  (do-benchmarks
    "Hiccup (type-hinted)"
    (simple-hiccup-hint)
    (list-hiccup-hint 50)
    (list-hiccup-hint 1000)))

(defn clabango-benches []
  (do-benchmarks
    "Clabango (string)"
    (simple-clabango-no-fd)
    (list-clabango-no-fd 50)
    (list-clabango-no-fd 1000)))


(defn clabango-benches-file []
  (do-benchmarks
    "Clabango (file)"
    (simple-clabango)
    (list-clabango 50)
    (list-clabango 1000)))

(defn selmer-benches []
  (do-benchmarks
    "Selmer (string)"
    (simple-selmer-no-fd)
    (list-selmer-no-fd 50)
    (list-selmer-no-fd 1000)))

(defn selmer-benches-file []
  (do-benchmarks
    "Selmer (file)"
    (simple-selmer)
    (list-selmer 50)
    (list-selmer 1000)))

(defn pebble-benches []
  (do-benchmarks
    "Pebble (string)"
    (simple-pebble-no-fd)
    (list-pebble-no-fd 50)
    (list-pebble-no-fd 1000)))

(defn pebble-benches-file []
  (do-benchmarks
    "Pebble (file)"
    (simple-pebble)
    (list-pebble 50)
    (list-pebble 1000)))

(defn stencil-benches []
  (do-benchmarks
    "Stencil (string)"
    (simple-stencil-no-fd)
    (list-stencil-no-fd 50)
    (list-stencil-no-fd 1000)))

(defn stencil-benches-file []
  (do-benchmarks
    "Stencil (file)"
    (simple-stencil)
    (list-stencil 50)
    (list-stencil 1000)))

(defn mustache-benches []
  (do-benchmarks
    "Mustache"
    (simple-mustache)
    (list-mustache 50)
    (list-mustache 1000)))

(defn tinsel-benches []
  (do-benchmarks
    "Tinsel"
    (simple-tinsel)
    (list-tinsel 50)
    (list-tinsel 1000)))

#_(defn laser-benches []
  (do-benchmarks
    "Laser"
    (simple-laser)
    (list-laser 50)
    (list-laser 1000)))

#_(defn laser-benches-hinted []
  (do-benchmarks
    "Laser (type-hinted)"
    (simple-laser-hinted)
    (list-laser-hinted 50)
    (list-laser-hinted 1000)))

(defn enlive-benches []
  (do-benchmarks
    "Enlive"
    (simple-enlive)
    (list-enlive 50)
    (list-enlive 1000)))

(defn run-benchmarks []
  (doall
    (conj
      []
      (selmer-benches)
      (selmer-benches-file)
      (pebble-benches)
      (pebble-benches-file)
      (mustache-benches)
      (stencil-benches)
      (stencil-benches-file)
      (str-benches)
      (hiccup-benches)
      (hiccup-benches-hinted)
      (clabango-benches)
      (clabango-benches-file)
      #_(laser-benches)
      #_(laser-benches-hinted)
      (enlive-benches)
      (tinsel-benches)
      )))

(defn to-microsecs [t]
  (* t 1000000))

(defn get-std-dev [variance]
  (to-microsecs (Math/sqrt variance)))

(defn simplify-results [results]
  (->> results
       (map
         (fn [{:keys [simple small-list big-list] :as result}]
           (assoc result
                  :simple (to-microsecs (first (:mean simple)))
                  :simple-std-dev (get-std-dev (first (:variance simple)))
                  :small-list (to-microsecs (first (:mean small-list)))
                  :small-list-std-dev (get-std-dev (first (:variance small-list)))
                  :big-list (to-microsecs (first (:mean big-list)))
                  :big-list-std-dev (get-std-dev (first (:variance big-list)))
                  )))
       (sort-by :name)))

(defn to-csv [simplified-results]
  (map
    (fn [{:keys [name simple simple-std-dev small-list small-list-std-dev big-list big-list-std-dev]}]
      [name
       (str simple)
       (str simple-std-dev)
       (str small-list)
       (str small-list-std-dev)
       (str big-list)
       (str big-list-std-dev)])
    simplified-results))

(defn -main [& args]
  (let [results (run-benchmarks)
        results-table (simplify-results results)]
    (clojure.pprint/print-table [:name :simple :simple-std-dev :small-list :small-list-std-dev :big-list :big-list-std-dev] results-table)
    (as-> results-table x
          (to-csv x)
          (write-csv x :force-quote true)
          (spit "results.csv" x))))

