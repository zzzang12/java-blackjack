package blackjack;

public class Card {
	private final String denomination;
	private final String suit;

	public Card(String denomination, String suit) {
		this.denomination = denomination;
		this.suit = suit;
	}

	public Card(Card shuffledCard) {
		this.denomination = shuffledCard.denomination;
		this.suit = shuffledCard.suit;
	}

	@Override
	public String toString() {
		return denomination + suit;
	}
}
