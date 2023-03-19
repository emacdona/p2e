(ns net.edmacdonald.pathfinder.entities
  (:require [clojure.string :as str]))


(def entity-types (atom #{}))
(def all-entities (atom {}))

;; Someday, the functions that follow this macro will be more specialized. For now, they
;; are all the same. As long as they remain the same, I'd rather have clojure write them
;; for me... instead of having to do that work myself.
(defmacro key-name-map
  [name]
  `(let [type# (keyword (name '~name))]
     (defn ~name [name#]
       (let [key# (keyword (#(str/replace % #"\s+" "_")
                             name#))
             entity# {:key  key#
                      :name name#
                      :type type#}]

         ;; keep track of entity types we're creating
         (swap! entity-types #(conj % type#))
         (swap! all-entities #(assoc % type#
                                        (conj (% type#)
                                              entity#)))
         entity#))
     (defn ~(symbol (str name "-decorate"))
       [entities# metadata-map# new-key#]
       (let [new-entities# (merge-meta entities# metadata-map# new-key#)]
         (swap! all-entities
                #(assoc % type# new-entities#))
         new-entities#))))

(defn merge-meta
  [merge-target keyed-meta meta-key-name]
  (map
    (fn [x] (assoc x meta-key-name (keyed-meta (x :key))))
    merge-target))

(defn to-map
  "Give a set of keyed entities (ie: each has a :key), create a mapping of :key's to entity"
  [set key]
  (apply merge
         (map (fn [x] (assoc {} (key x) x))
              set)))

(load "entity_abilities")
(load "entity_ancestries")
(load "entity_ancestry_entries")
(load "entity_backgrounds")
(load "entity_character_classes")
(load "entity_conditions")
(load "entity_feats")
(load "entity_languages")
(load "entity_skills")







