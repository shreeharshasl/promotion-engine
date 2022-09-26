# promotion-engine
promotion engine to apply different kind of promotions and serve add to cart

Our Cart contains a list of single character SKU ids (A, B, C....) over which the promotion engine will
need to run.
The promotion engine will need to calculate the total order value after applying the 2
promotion types
 buy &#39;n&#39; items of a SKU for a fixed price (3 A&#39;s for 130)
 buy SKU 1 &amp; SKU 2 for a fixed price ( C + D = 30 )
The promotion engine should be modular to allow for more promotion types to be added at a
later date (e.g. a future promotion could be x% of a SKU unit price). For this coding exercise
you can assume that the promotions will be mutually exclusive; in other words if one is
applied the other promotions will not apply
Test Setup
Unit price for SKU IDs
A 50
B 30
C 20
D 15
Active Promotions
3 of A&#39;s for 130
2 of B&#39;s for 45
C &amp; D for 30
Scenario A
1 * A 50
1 * B 30
1 * C 20
======
Total 100

Classification: Internal
Scenario B
5 * A 130 + 2*50
5 * B 45 + 45 + 30
1 * C 20
======
Total 370

Scenario C
3 * A 130
5 * B 45 + 45 + 1 * 30
1 * C -
1 * D 30
======
Total 280


APP DETAILS:
Promo
    Endpoint: http://localhost:8080/promotion
    HTTP METHOD: PUT 
    Request Sample: {
            "promoId": "2",
            "promoType": "SAME",
            "priority": 0,
            "promoAmount": 20,
            "promoItemList": [
                    {
                    "skuId": "B",
                    "count": 2
                    }
                ]
            }