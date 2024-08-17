package blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game {
	private Set<Card> alreadyCards;

	public Game() {
		this.alreadyCards = new HashSet<>();
	}

	public void start() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		Scanner scanner = new Scanner(System.in);
		String names = scanner.nextLine();
		List<Player> players = Arrays.stream(names.split(",")).map(Player::new).toList();
		Player dealer = new Player("딜러");

		System.out.println("딜러와 " + String.join(", ", players.stream().map(Player::getName).toList()) + "에게 2장의 카드를 나누었습니다.");

		List<Card> shuffledDeck = shuffleDeck(createDeck());
//		1.
//		dealer.addCards(generateCard(), generateCard());
//		2.
//		dealer.addCard(generateCard());
//		dealer.addCard(generateCard());
//		3.
//		for (int i = 0; i < 2; i++) {
//			dealer.addCard(generateCard());
//		}
//		4.
//    IntStream.range(0, 1).forEach(() -> dealer.addCard(generateCard()));
//		5.
//		dealer.addCard(generateCard())
//		      .addCard(generateCard())
//		      .addCards(IntStream.range(0, 2).mapToObj(i -> generateCard()).collect(Collectors.toSet()));
//		6.
//		dealer.addCards(IntStream.range(0, 2).mapToObj(i -> generateCard()).collect(Collectors.toSet()));

		dealer.addFirstCards(generateCards(shuffledDeck));

		players.forEach(player -> player.addFirstCards(generateCards(shuffledDeck)));

		System.out.println(dealer);
		players.forEach(System.out::println);

		players.forEach(player -> {
			System.out.println(player + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
			String names = scanner.nextLine();

		});
	}

	private Card generateCard(List<Card> shuffledDeck) {
		Card newCard = new Card(shuffledDeck.remove(shuffledDeck.size() - 1));
		alreadyCards.add(newCard);
		return newCard;
	}

	private List<Card> generateCards(List<Card> shuffledDeck) {
		List<Card> cards = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			cards.add(generateCard(shuffledDeck));
		}
		return cards;
	}

	private boolean isDuplicateCard(Card card) {
		return alreadyCards.contains(card);
	}

	private String newDenomination() {
		Random random = new Random();
		int randIdx = random.nextInt(Util.denominationCandidates.size());
		return Util.denominationCandidates.get(randIdx);
	}

	private String newSuit() {
		Random random = new Random();
		int randIdx = random.nextInt(Util.suitCandidates.size());
		return Util.suitCandidates.get(randIdx);
	}

	private static List<Card> createDeck() {
		return Util.denominationCandidates.stream()
		                             .flatMap(denomination -> Util.suitCandidates.stream()
		                                                                    .map(suit -> new Card(denomination, suit)))
		                             .collect(Collectors.toList());
	}

	private static List<Card> shuffleDeck(List<Card> deck) {
		Collections.shuffle(deck);
		return deck;
	}

}
