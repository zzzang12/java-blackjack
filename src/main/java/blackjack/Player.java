package blackjack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Player {
	private final String name;
	private final List<Card> cards;

	public Player(String name) {
		this.name = name;
		this.cards = new ArrayList<>();
	}

	public String getName() {
		return name;
	}


	public void addCards(Collection<Card> cards) {
		this.cards.addAll(new ArrayList<>(cards));
	}

	public Player addCard(Card card) {
		this.cards.add(card);
		return this;
	}

	public void addFirstCards(Collection<Card> cards) {
		for (int i = 0; i < 2; i++) {
			this.addCards(cards);
		}
	}
}
