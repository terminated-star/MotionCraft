# MotionCraft

Bhopping, as a server-side Minecraft plugin/mod.
Currently, not production-ready.

## Goals

- Be compatible with versions from 1.8.8 to the latest.
- Be decently performant.
- Be decently customizable.

MotionCraft will always be free and open source.

## Current State

Currently, only available on servers that implement the Spigot API.

## Disclaimer

To emulate custom physics, this project updates the player's velocity every tick. This creates an incompatibility with most anti-cheats. In the best case scenario with an incompatible anti-cheat, most checks won't function. In the worst case scenario, the anti-cheat will false flag players. You will most likely need to use an anti-cheat that had this project in mind.

### Anti-cheat status

These tests were taken on Sep 12, 2022 on a 1.18.2 paper server and a 1.18.2 client. Only anti-cheats that have been updated this year were tested.
I only tested the anti-cheats for speed and flight, since there are a lot. I tested using the anti-cheats' default config.
I would only recommend using anti-cheats in the `decent` section and above.

#### Best compatibility

Matrix (Premium) - Few checks disabled. The free version is untested.

#### Decent compatibility

AntiAura (V2) - Some checks disabled.

#### Low compatibility

Spartan - Most checks disabled.
Vulcan - Most checks disabled.

#### Worst compatibility

Grim - False flags.
Negativity (V1 & V2) - False flags.
Themis - False flags.
Wraith - False flags.
NoCheatPlus - False flags.
