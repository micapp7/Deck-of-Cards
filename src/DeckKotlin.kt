import java.util.Collections
import java.util.Optional
import java.util.Stack
import java.util.stream.IntStream

class DeckKotlin private constructor() {
    private val deckCards: Stack<CardKotlin>

    init {
        this.deckCards = initDeck()
    }

    private fun initDeck(): Stack<CardKotlin> {
        val deckCards = Stack<CardKotlin>()
        for (suit in CardKotlin.Suit.values()) {
            for (rank in CardKotlin.Rank.values()) {
                deckCards.push(CardKotlin.getCard(rank, suit))
            }
        }

        Collections.shuffle(deckCards)
        return deckCards

    }

    // Only public method on deck.
    fun deal(): Optional<CardKotlin> {
        return if (this.deckCards.empty())
            Optional.empty()
        else
            Optional.of(this.deckCards.pop())
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val deck = DeckKotlin()
            val numCardsToDeal = 52
            IntStream.range(0, numCardsToDeal).forEach { value -> println(deck.deal()) }
        }
    }
}
