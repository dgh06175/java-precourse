package store.model.store;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import store.model.promotion.Promotion;
import store.model.promotion.PromotionStatus;
import store.model.promotion.PromotionStatusQuantity;
import store.model.storage.Storage;

class StoreTest {
    private final Store store;

    public StoreTest() {
        Storage storage = new Storage(getProductData());
        Promotion promotion = new Promotion(getPromotionConfigData());
        this.store = new Store(storage, promotion);
    }

    public static List<String> getProductData() {
        return List.of(
                "name,price,quantity,promotion",
                "콜라,1000,10,탄산2+1",
                "콜라,1000,10,null",
                "사이다,1000,8,탄산2+1",
                "사이다,1000,7,null",
                "오렌지주스,1800,9,MD추천상품",
                "탄산수,1200,5,탄산2+1",
                "물,500,10,null",
                "비타민워터,1500,6,null",
                "감자칩,1500,5,반짝할인",
                "감자칩,1500,5,null",
                "초코바,1200,5,MD추천상품",
                "초코바,1200,5,null",
                "에너지바,2000,5,null",
                "정식도시락,6400,8,null",
                "컵라면,1700,1,MD추천상품",
                "컵라면,1700,10,null"
        );
    }

    public static List<String> getPromotionConfigData() {
        return List.of(
                "name,buy,get,start_date,end_date",
                "탄산2+1,2,1,2024-01-01,2024-12-31",
                "MD추천상품,1,1,2024-01-01,2024-12-31",
                "반짝할인,1,1,2024-11-01,2024-11-30"
        );
    }

    @Nested
    class CheckPromotionStatusTests {
        @Test
        void 특이사항_없음() {
            // given
            String name = "콜라";
            int quantity = 3;

            // when
            PromotionStatusQuantity promotionStatusQuantity = store.checkPromotionStatus(name, quantity);

            // then
            assertThat(promotionStatusQuantity.promotionStatus()).isEqualTo(PromotionStatus.OK);
            assertThat(promotionStatusQuantity.quantity()).isEqualTo(0);
        }

        @Test
        void 특이사항_없음2() {
            // given
            String name = "에너지바";
            int quantity = 5;

            // when
            PromotionStatusQuantity promotionStatusQuantity = store.checkPromotionStatus(name, quantity);

            // then
            assertThat(promotionStatusQuantity.promotionStatus()).isEqualTo(PromotionStatus.OK);
            assertThat(promotionStatusQuantity.quantity()).isEqualTo(0);
        }

        @Test
        void 프로모션_재고_안쪽이면_OK_반환() {
            // MARK: 재고 부족 + 그냥 가격으로 결제 안내해도 기획상 문제 없을거 같기도 함
            // given
            String name = "사이다";
            int quantity = 8;

            // when
            PromotionStatusQuantity promotionStatusQuantity = store.checkPromotionStatus(name, quantity);

            // then
            assertThat(promotionStatusQuantity.promotionStatus()).isEqualTo(PromotionStatus.OK);
        }

        @Test
        void 프로모션_재고_부족() {
            // given
            String name = "콜라";
            int quantity = 13;

            // when
            PromotionStatusQuantity promotionStatusQuantity = store.checkPromotionStatus(name, quantity);

            // then
            assertThat(promotionStatusQuantity.promotionStatus()).isEqualTo(PromotionStatus.PROMOTION_STOCK_NOT_ENOUGH);
            assertThat(promotionStatusQuantity.quantity()).isEqualTo(4);
        }

        @Test
        void 프로모션_상품을_더_받울수_있다는_값을_반환한다() {
            // given
            String name = "오렌지주스";
            int quantity = 1;

            // when
            PromotionStatusQuantity promotionStatusQuantity = store.checkPromotionStatus(name, quantity);

            // then
            assertThat(promotionStatusQuantity.promotionStatus()).isEqualTo(PromotionStatus.BONUS_OFFER_AVAILABLE);
            assertThat(promotionStatusQuantity.quantity()).isEqualTo(1);
        }
    }
}