import java.util.Collections
import java.util.HashMap

class CardKotlin private constructor(private val rank: Rank,
                                     private val suit: Suit) : Comparable<CardKotlin> {

    override fun toString(): String {
        return String.format("%s of %s", this.rank, this.suit)
    }

    override fun compareTo(o: CardKotlin): Int {

        val rankComparison = Integer.compare(this.rank.rankValue,
                o.rank.rankValue)

        return if (rankComparison != 0) {
            rankComparison
        } else Integer.compare(this.suit.suitValue, o.suit.suitValue)

    }

    enum class Suit constructor(val suitValue: Int) {
        DIAMOND(1),
        CLUBS(2),
        HEARTS(3),
        SPADES(4)
    }

    enum class Rank constructor(val rankValue: Int) {
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(11),
        QUEEN(12),
        KING(13),
        ACE(14)
    }

    companion object {

        private val CARD_CACHE = initCache()

        private fun initCache(): Map<String, CardKotlin> {
            val cache = HashMap<String, CardKotlin>()

            for (suit in Suit.values()) {
                for (rank in Rank.values()) {
                    cache[cardKey(rank, suit)] = CardKotlin(rank, suit)
                }
            }

            return Collections.unmodifiableMap(cache)
        }

        fun getCard(rank: Rank,
                    suit: Suit): CardKotlin {
            val card = CARD_CACHE[cardKey(rank, suit)]

            if (card != null) {
                return card
            }

            throw RuntimeException("Invalid card! $rank $suit")

        }

        private fun cardKey(rank: Rank, suit: Suit): String {
            return rank.toString() + " of " + suit
        }
    }
}
