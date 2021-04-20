import random


class Account:
    def __init__(self, player=1):
        self.player = player
        self.money = 5000
        self.count = 0
        self.hand = []


class Blackjack:
    def __init__(self, player=1):

        self.player = Account()
        self.dealer = Account(0)

        self.cards_new = [
            2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8, 9, 9, 9, 9,
            10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11
        ]
        self.cards = []
        self.pot = 0
        self.start()

    def start(self, ingame: bool = True):

        while ingame:
            self._init_game()
            print(self.player.money)
            einsatz = int(input("einsatz bitte \n"))
            self.player.money = max(0, self.player.money - einsatz)
            # self.pot = einsatz
            while ingame:
                print(self.player.hand, self.player.count, "\n", self.dealer.hand[0])
                choice = input("0:hit 1:stay\n")
                if "1" in choice:
                    break
                else:
                    self.player.hand.append(self.cards.pop(random.randrange(len(self.cards))))
                    self.player.count = sum(self.player.hand)
                if self.player.count > 21:
                    self.player.count = -1
                    break

            while self.player.count != -1 and self.dealer.count < 17:
                self.dealer.hand.append(self.cards.pop(random.randrange(len(self.cards))))
                self.dealer.count = sum(self.dealer.hand)

            print(self.player.hand, self.player.count, "\n", self.dealer.hand, self.dealer.count)

            if self.player.count < self.dealer.count < 22:
                print("loose")
                if self.player.money == 0:
                    ingame = False
            elif self.player.count == self.dealer.count:
                print("push")
                self.player.money += einsatz
            else:
                print("win")
                self.player.money += 2 * einsatz

    def _init_game(self):
        self.cards = self.cards_new.copy()
        print(self.cards)
        self.dealer.hand = []
        self.player.hand = []
        self.dealer.hand.append(self.cards.pop(random.randrange(len(self.cards))))
        self.dealer.hand.append(self.cards.pop(random.randrange(len(self.cards))))
        self.dealer.count = sum(self.dealer.hand)

        self.player.hand.append(self.cards.pop(random.randrange(len(self.cards))))
        self.player.hand.append(self.cards.pop(random.randrange(len(self.cards))))
        self.player.count = sum(self.player.hand)

if __name__ == '__main__':
    Blackjack: Blackjack = Blackjack()
