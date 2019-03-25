package lottery.domain;

import org.junit.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class LotteryWinningStatisticsTest {

    @Test
    public void test_티켓_10개_당첨_없음() {
        WinningTicket winningTicket = new WinningTicket(Arrays.asList(1, 2, 3, 4, 5, 6), 10);
        List<LotteryTicket> myTickets = createTickets(Arrays.asList(),
                10, Arrays.asList(11, 12, 13, 14, 15, 16));

        LotteryWinningStatistics statistics = new LotteryWinningStatistics(winningTicket, myTickets);
        assertThat(statistics.countRank(LotteryRank.NONE).amount)
                .isEqualTo(10);

        assertThat(statistics.revenueRate().rate)
                .isEqualTo(0.00);
    }

    @Test
    public void test_티켓_10개중_1등_1개() {
        WinningTicket winningTicket = new WinningTicket(Arrays.asList(1, 2, 3, 4, 5, 6), 10);
        LotteryTicket firstRankTicket = new LotteryTicket(Arrays.asList(1, 2, 3, 4, 5, 6));

        List<LotteryTicket> myTickets = createTickets(Arrays.asList(firstRankTicket),
                9, Arrays.asList(11, 12, 13, 14, 15, 16));

        LotteryWinningStatistics statistics = new LotteryWinningStatistics(winningTicket, myTickets);

        assertThat(statistics.countRank(LotteryRank.FIRST).amount)
                .isEqualTo(1);
        assertThat(statistics.countRank(LotteryRank.NONE).amount)
                .isEqualTo(9);

        assertThat(statistics.revenueRate().rate)
                .isEqualTo(200_000.00);
    }

    @Test
    public void test_티켓_10개중_2등_1개() {
        WinningTicket winningTicket = new WinningTicket(Arrays.asList(1, 2, 3, 4, 5, 6), 10);
        LotteryTicket secondRankTicket = new LotteryTicket(Arrays.asList(2, 3, 4, 5, 6, 10));

        List<LotteryTicket> myTickets = createTickets(Arrays.asList(secondRankTicket),
                9, Arrays.asList(11, 12, 13, 14, 15, 16));

        LotteryWinningStatistics statistics = new LotteryWinningStatistics(winningTicket, myTickets);

        assertThat(statistics.countRank(LotteryRank.SECOND).amount)
                .isEqualTo(1);
        assertThat(statistics.countRank(LotteryRank.NONE).amount)
                .isEqualTo(9);

        assertThat(statistics.revenueRate().rate)
                .isEqualTo(3_000.0);
    }

    @Test
    public void test_티켓_10개중_3등_1개() {
        WinningTicket winningTicket = new WinningTicket(Arrays.asList(1, 2, 3, 4, 5, 6), 10);
        LotteryTicket thirdRankTicket = new LotteryTicket(Arrays.asList(2, 3, 4, 5, 6, 7));
        List<LotteryTicket> myTickets = createTickets(Arrays.asList(thirdRankTicket),
                9, Arrays.asList(11, 12, 13, 14, 15, 16));

        LotteryWinningStatistics statistics = new LotteryWinningStatistics(winningTicket, myTickets);

        assertThat(statistics.countRank(LotteryRank.THIRD).amount)
                .isEqualTo(1);
        assertThat(statistics.countRank(LotteryRank.NONE).amount)
                .isEqualTo(9);

        assertThat(statistics.revenueRate().rate)
                .isEqualTo(150.00);
    }

    @Test
    public void test_티켓_10개중_4등_1개() {
        WinningTicket winningTicket = new WinningTicket(Arrays.asList(1, 2, 3, 4, 5, 6), 10);
        LotteryTicket fourthRankTicket = new LotteryTicket(Arrays.asList(3, 4, 5, 6, 7, 8));
        List<LotteryTicket> myTickets = createTickets(Arrays.asList(fourthRankTicket),
                9, Arrays.asList(11, 12, 13, 14, 15, 16));

        LotteryWinningStatistics statistics = new LotteryWinningStatistics(winningTicket, myTickets);

        assertThat(statistics.countRank(LotteryRank.FOURTH).amount)
                .isEqualTo(1);
        assertThat(statistics.countRank(LotteryRank.NONE).amount)
                .isEqualTo(9);

        assertThat(statistics.revenueRate().rate)
                .isEqualTo(5.00);
    }

    @Test
    public void test_티켓_10개중_5등_1개() {
        WinningTicket winningTicket = new WinningTicket(Arrays.asList(1, 2, 3, 4, 5, 6), 10);
        LotteryTicket fifthRankTicket = new LotteryTicket(Arrays.asList(4, 5, 6, 7, 8, 9));
        List<LotteryTicket> myTickets = createTickets(Arrays.asList(fifthRankTicket),
                9, Arrays.asList(11, 12, 13, 14, 15, 16));

        LotteryWinningStatistics statistics = new LotteryWinningStatistics(winningTicket, myTickets);

        assertThat(statistics.countRank(LotteryRank.FIFTH).amount)
                .isEqualTo(1);
        assertThat(statistics.countRank(LotteryRank.NONE).amount)
                .isEqualTo(9);

        assertThat(statistics.revenueRate().rate)
                .isEqualTo(0.50);
    }

    private List<LotteryTicket> createTickets(List<LotteryTicket> designatedNumbersTicket, int losingTicketSize, List<Integer> losingNumbers) {
        final List<LotteryTicket> result = IntStream.range(0, losingTicketSize)
                .mapToObj(i -> new LotteryTicket(losingNumbers))
                .collect(Collectors.toList());

        result.addAll(designatedNumbersTicket);
        return result;
    }
}
