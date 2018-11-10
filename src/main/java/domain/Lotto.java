package domain;

import utils.LottoGenerator;

import java.util.List;

public class Lotto {
    public static final int LOTTO_CHOICE_CNT = 6;
    public static final int LOTTO_PRICE = 1000;

    private List<Integer> numbers;

    public Lotto() {
        this(LottoGenerator.generateNumberList(LOTTO_CHOICE_CNT));

    }

    public Lotto(int number) {
        this(LottoGenerator.generateNumberList(number));
    }

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int getCombineCount(Lotto lastLotto) {
        if (lastLotto == null) {
            return 0;
        }
        return (int) numbers.stream().filter(obj -> lastLotto.isContains(obj)).count();
    }

    private boolean isContains(Integer number) {
        return numbers.contains(number);
    }

    public int getNumCount() {
        return numbers.size();
    }

}