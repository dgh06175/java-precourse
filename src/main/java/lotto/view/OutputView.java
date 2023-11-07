package lotto.view;

import java.util.*;
import lotto.domain.LottoRank;
import java.text.DecimalFormat;
import lotto.domain.util.Constant;

public class OutputView {
    public void printInputMoneyMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printInputWinningNumbersMessage() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
    }

    public void printInputBonusNumberMessage() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
    }

    public void printLottoNumbers(List<List<Integer>> lottosNumbers) {
        System.out.printf("\n%s개를 구매했습니다.%n", lottosNumbers.size());
        for (List<Integer> lottoNumbers : lottosNumbers) {
            System.out.println(lottoNumbers);
        }
    }

    public void printRanksCount(Map<LottoRank, Integer> rankCount) {
        System.out.println("\n당첨 통계\n---");
        DecimalFormat formatter = new DecimalFormat("###,###");

        for(LottoRank lottoRank: rankCount.keySet()) {
            if (lottoRank == LottoRank.NONE) continue;
            int matchCount = lottoRank.getMatchCount();
            String prize = formatter.format(lottoRank.getPrize());
            int count = rankCount.get(lottoRank);
            String message = "일치";
            if (lottoRank == LottoRank.SECOND) message = "일치, 보너스 볼 일치";
            System.out.printf("%d개 %s (%s원) - %d개\n", matchCount, message, prize, count);
        }
    }

    public void printRateOfProfits(Map<LottoRank, Integer> rankCount) {
        int lottoCount = rankCount.values().stream().mapToInt(Integer::intValue).sum();
        int inMoney = lottoCount * Constant.LOTTO_PRICE;
        int outMoney = rankCount.keySet()
                .stream()
                .mapToInt(rank -> rank.getPrize() * rankCount.get(rank))
                .sum();
        double rateOfProfit = (double) outMoney / inMoney * 100.0;
        System.out.printf("총 수익률은 %.1f%%입니다.", rateOfProfit);
    }
}
