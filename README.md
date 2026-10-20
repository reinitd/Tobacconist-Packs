# Tobacconist: Packs

This is a 3rd party add-on to the [Tobacconist](https://github.com/diggydwarff/tobacconist) Minecraft mod. \
This mod adds various cigarette packs and cigar boxes to the game, where you can store Tobacconist cigarettes and cigars respectively.

NeoForge 1.21.1 only (NeoForge 21.1.256 or newer). \
Requires Tobacconist 4.1.0 or newer. Tested to work with Tobacconist 4.2.0 Beta 2, and should work with 4.1.0 stable as well. \
Install on both Client and Server.

EMI/JEI recommended.

## Cigarette Packs

Each pack holds 20 cigarettes. Right click a pack to open it.

To get the stylized cigarette packs, you must first craft a blank pack, which is a 2x3 grid of paper. \
Then craft the blank pack with the ingredients below (shapeless). Any cigarettes already in the blank pack stay in the branded pack. \
Only blank packs can be branded.

| Pack | Blank pack + |
|---|---|
| American Spirit Black | arrow + black dye |
| American Spirit Light Blue | arrow + light blue dye |
| Camel Blue | brown dye + blue dye |
| Camel Crush | brown dye + snowball |
| Camel Gold | brown dye + yellow dye |
| Camel Royal | brown dye + red dye + gold nugget |
| Chesterfield Filters (Vintage) | red dye + white wool |
| LD Blue | gunpowder + blue dye |
| L&M Blue | blue dye + white dye |
| Lucky Strike Red | flint + red dye |
| Marlboro Gold | leather + yellow dye |
| Marlboro Red | leather +  red dye |
| Newport | cyan dye + snowball |

## Cigar Boxes

Each box holds 9 cigars. Right click a box to open it. \
Has a dynamic texture dictated by the amount of cigars inside (empty, quarter, half, three quarter, full).

Craft a glass pane above a log (or wood, stripped log or stripped wood) of the matching type:

| Box | Glass pane above |
|---|---|
| Oak, Spruce, Birch, Jungle, Acacia, Dark Oak, Mangrove, Cherry | that wood's log |
| Pale Oak | pale oak log, **only with a mod that backports pale oak** (pale oak isn't in vanilla 1.21.1). Obtainable in Creative. |
| Crimson, Warped | that stem |
| Otherworldly (chorus) | chorus flower |

Without a pale oak backport, the Pale Oak Cigar Box is only available in creative. If your backport's logs aren't recognized, their item ID can be added to `data/tobacconistpacks/tags/item/pale_oak_logs.json`.

## Credits

- reinitd (developer)
- vantwister (art)
- [Tobacconist](https://github.com/diggydwarff/tobacconist) (inspiration) - very well put-together and coherent mod. I highly recommend adding it to your world.

## License

The code is licensed under MIT ([LICENSE](LICENSE)), while the textures and logo are under CC-BY-NC-ND 4.0 ([LICENSE-ASSETS](LICENSE-ASSETS)).

## Disclaimers

I am not a Java dev, and I have never modded Minecraft. This mod was made to satisfy a need for an SMP with some friends. Contributors are free to make a PR, and requests will likely be honored. \
I did utilize Claude for the GUI portion, as I despise doing GUIs with all my heart.

Not affiliated with, endorsed by, or connected to the [Tobacconist](https://github.com/diggydwarff/tobacconist) mod. \
Not affiliated with, endorsed by, or connected to any tobacco company. Brand names belong to their respective owners.