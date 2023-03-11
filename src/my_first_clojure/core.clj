(ns my-first-clojure.core
  (:require [clojure.string :as str]))

;;(import clojure.string/lower-case)

(defn key-name [str] (str/replace str #"\s+" "_"))

;; Someday, the functions that follow this macro will be more specialized. For now, they
;; are all the same. As long as they remain the same, I'd rather have clojure write them
;; for me... instead of having to do that work myself.
(defmacro key-name-map
  [name]
  `(defn ~name [name#]
     {
      :key  (keyword (key-name name#))
      :name name#
      :type (keyword (name '~name))
      }))

(key-name-map ability)
(key-name-map condition)
(key-name-map ancestry-entry)
(key-name-map ancestry)
(key-name-map background)
(key-name-map language)
(key-name-map character-class)
(key-name-map skill)
(key-name-map feat)

(defn get-item
  "Find Map in [set] whose :key field is :[namespace]/(name [:key])"
  [set key namespace]
  (first
    (filter (fn [x]
              (= (x :key)
                 (keyword namespace (name key))))
            set)))

(def abilities #{(ability "strength")
                 (ability "dexterity")
                 (ability "constitution")
                 (ability "intelligence")
                 (ability "wisdom")
                 (ability "charisma")})

(def conditions #{(condition "blinded")
                  (condition "broken")
                  (condition "clumsy")
                  (condition "concealed")
                  (condition "confused")
                  (condition "controlled")
                  (condition "dazzled")
                  (condition "deafened")
                  (condition "doomed")
                  (condition "drained")
                  (condition "dying")
                  (condition "encumbered")
                  (condition "enfeebled")
                  (condition "fascinated")
                  (condition "fatigued")
                  (condition "flat-footed")
                  (condition "fleeing")
                  (condition "friendly")
                  (condition "frightened")
                  (condition "grabbed")
                  (condition "helpful")
                  (condition "hidden")
                  (condition "hostile")
                  (condition "immobilized")
                  (condition "indifferent")
                  (condition "invisible")
                  (condition "observed")
                  (condition "paralyzed")
                  (condition "persistent damage")
                  (condition "petrified")
                  (condition "prone")
                  (condition "quickened")
                  (condition "restrained")
                  (condition "sickened")
                  (condition "slowed")
                  (condition "stunned")
                  (condition "stupefied")
                  (condition "unconscious")
                  (condition "undetected")
                  (condition "unfriendly")
                  (condition "unnoticed")
                  (condition "wounded")})

(def ancestry-entries #{(ancestry-entry "hit points")
                        (ancestry-entry "size")
                        (ancestry-entry "speed")
                        (ancestry-entry "ability boosts")
                        (ancestry-entry "ability flaw")
                        (ancestry-entry "languages")
                        (ancestry-entry "traits")
                        (ancestry-entry "special abilities")
                        (ancestry-entry "heritages")
                        (ancestry-entry "ancestry feats")})

(def ancestries #{(ancestry "dwarf")
                  (ancestry "elf")
                  (ancestry "gnome")
                  (ancestry "goblin")
                  (ancestry "halfling")
                  (ancestry "human")})

(def backgrounds #{(background "acolyte")
                   (background "acrobat")
                   (background "animal whisperer")
                   (background "artisan")
                   (background "artist")
                   (background "barkeep")
                   (background "barrister")
                   (background "bounty hunter")
                   (background "charlatan")
                   (background "criminal")
                   (background "detective")
                   (background "emissary")
                   (background "entertainer")
                   (background "farmhand")
                   (background "field medic")
                   (background "fortune teller")
                   (background "gambler")
                   (background "gladiator")
                   (background "guard")
                   (background "herbalist")
                   (background "hermit")
                   (background "hunter")
                   (background "laborer")
                   (background "martial disciple")
                   (background "merchant")
                   (background "miner")
                   (background "noble")
                   (background "nomad")
                   (background "prisoner")
                   (background "sailor")
                   (background "scholar")
                   (background "scout")
                   (background "street_urchin")
                   (background "tinker")
                   (background "warrior")})

(def languages #{(language "common")
                 (language "draconic")
                 (language "dwarven")
                 (language "elven")
                 (language "gnomish")
                 (language "goblin")
                 (language "halfling")
                 (language "jotun")
                 (language "orcish")
                 (language "sylvan")
                 (language "undercommon")
                 (language "abyssal")
                 (language "aklo")
                 (language "aquan")
                 (language "auran")
                 (language "celestial")
                 (language "gnoll")
                 (language "ignan")
                 (language "infernal")
                 (language "necril")
                 (language "shadowtongue")
                 (language "terran")
                 (language "druidic")})

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

(def skills #{(skill "acrobatics")
              (skill "arcana")
              (skill "athletics")
              (skill "crafting")
              (skill "deception")
              (skill "diplomacy")
              (skill "intimidation")
              (skill "lore")
              (skill "medicine")
              (skill "nature")
              (skill "occultism")
              (skill "performance")
              (skill "religion")
              (skill "society")
              (skill "stealth")
              (skill "survival")
              (skill "thievery")})

(def feats #{(feat "adopted ancestry")
             (feat "armor proficiency")
             (feat "breath control")
             (feat "canny acumen")
             (feat "diehard")
             (feat "fast recovery")
             (feat "feather step")
             (feat "fleet")
             (feat "incredible initiative")
             (feat "ride")
             (feat "shield block")
             (feat "toughness")
             (feat "weapon proficiency")
             (feat "ancestral paragon")
             (feat "untrained improvisation")
             (feat "expeditious search")
             (feat "incredible investiture")
             (feat "assurance")
             (feat "dubious knowledge")
             (feat "quick identification")
             (feat "recognize spell")
             (feat "skill training")
             (feat "trick magic item")
             (feat "automatic knowledge")
             (feat "magical shorthand")
             (feat "quick recognition")
             (feat "cat fall")
             (feat "quick squeeze")
             (feat "steady balance")
             (feat "nimble crawl")
             (feat "kip up")
             (feat "arcane sense")
             (feat "unified theory")
             (feat "combat climber")
             (feat "hefty hauler")
             (feat "quick jump")
             (feat "titan wrestler")
             (feat "underwater marauder")
             (feat "powerful leap")
             (feat "rapid mantel")
             (feat "quick climb")
             (feat "quick swim")
             (feat "wall jump")
             (feat "cloud jump")
             (feat "alchemical crafting")
             (feat "quick repair")
             (feat "snare crafting")
             (feat "specialty crafting")
             (feat "magical crafting")
             (feat "impeccable crafting")
             (feat "inventory")
             (feat "craft anything")
             (feat "charming liar")
             (feat "lengthy diversion")
             (feat "lie to me")
             (feat "confabulator")
             (feat "quick disguise")
             (feat "slippery secrets")
             (feat "bargain hunter")
             (feat "group impression")
             (feat "hobnobber")
             (feat "glad-hand")
             (feat "shameless request")
             (feat "legendary negotiation")
             (feat "group coercion")
             (feat "intimidating glare")
             (feat "quick coercion")
             (feat "intimidating prowess")
             (feat "lasting coercion")
             (feat "battle cry")
             (feat "terrified retreat")
             (feat "scare to death")
             (feat "additional lore")
             (feat "experienced professional")
             (feat "unmistakable lore")
             (feat "legendary professional")
             (feat "battle medicine")
             (feat "continual recovery")
             (feat "robust recovery")
             (feat "ward medic")
             (feat "legendary medic")
             (feat "natural medicine")
             (feat "train animal")
             (feat "bonded animal")
             (feat "oddity identification")
             (feat "bizarre magic")
             (feat "fascinating performance")
             (feat "impressive performance")
             (feat "virtuosic performer")
             (feat "legendary performer")
             (feat "student of the canon")
             (feat "divine guidance")
             (feat "courtly graces")
             (feat "multilingual")
             (feat "read lips")
             (feat "sign language")
             (feat "streetwise")
             (feat "connections")
             (feat "legendary codebreaker")
             (feat "legendary linguist")
             (feat "experience smuggler")
             (feat "terrain stalker")
             (feat "quiet allies")
             (feat "foil senses")
             (feat "swift sneak")
             (feat "legendary sneak")
             (feat "experienced tracker")
             (feat "forager")
             (feat "survey wildlife")
             (feat "terrain expertise")
             (feat "planar survival")
             (feat "legendary survivalist")
             (feat "pickpocket")
             (feat "subtle theft")
             (feat "wary disarmament")
             (feat "quick unlock")
             (feat "legendary thief")
             })
