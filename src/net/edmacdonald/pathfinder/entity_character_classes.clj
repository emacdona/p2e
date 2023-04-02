(in-ns 'net.edmacdonald.pathfinder.entities)

(key-name-map character-class)

(def character-classes #{(character-class "alchemist")
                         (character-class "barbarian")
                         (character-class "bard")
                         (character-class "champion")
                         (character-class "cleric")
                         (character-class "druid")
                         (character-class "fighter")
                         (character-class "monk")
                         (character-class "ranger")
                         (character-class "rogue")
                         (character-class "sorcerer")
                         (character-class "wizard")})


(def key-ability-options {:alchemist [:intelligence]
                          :barbarian [:strength]
                          :bard      [:charisma]
                          :champion  [:strength :dexterity]
                          :cleric    [:wisdom]
                          :druid     [:wisdom]
                          :fighter   [:strength :dexterity]
                          :monk      [:strength :dexterity]
                          :ranger    [:strength :dexterity]
                          :rogue     [:dexterity :any]
                          :sorcerer  [:charisma]
                          :wizard    [:intelligence]
                          })

(def hit-points-base {:alchemist 8
                      :barbarian 12
                      :bard      8
                      :champion  10
                      :cleric    8
                      :druid     8
                      :fighter   10
                      :monk      10
                      :ranger    10
                      :rogue     8
                      :sorcerer  6
                      :wizard    6
                      })

(def character-classes-decorated
  (-> character-classes
      (character-class-decorate key-ability-options :key-ability-options)
      (character-class-decorate hit-points-base :hit-points-base)
      )
  )
