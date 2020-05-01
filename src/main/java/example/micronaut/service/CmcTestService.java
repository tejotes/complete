package example.micronaut.service;

import javax.inject.Singleton;

@Singleton
public class CmcTestService {

    private final static String STATIC_JSON_RESULT = "{\n" + "    \"data\": {\n" + "        \"1\": {\n" + "            \"id\": 1,\n" + "            \"name\": \"Bitcoin\",\n" + "            \"symbol\": \"BTC\",\n" + "            \"slug\": \"bitcoin\",\n" + "            \"is_active\": 1,\n" + "            \"is_fiat\": 0,\n" + "            \"circulating_supply\": 17199862,\n" + "            \"total_supply\": 17199862,\n" + "            \"max_supply\": 21000000,\n" + "            \"date_added\": \"2013-04-28T00:00:00.000Z\",\n" + "            \"num_market_pairs\": 331,\n" + "            \"cmc_rank\": 1,\n" + "            \"last_updated\": \"2018-08-09T21:56:28.000Z\",\n" + "            \"tags\": [\n" + "                \"mineable\"\n" + "            ],\n" + "            \"platform\": null,\n" + "            \"quote\": {\n" + "                \"USD\": {\n" + "                    \"price\": 6602.60701122,\n" + "                    \"volume_24h\": 4314444687.5194,\n" + "                    \"percent_change_1h\": 0.988615,\n" + "                    \"percent_change_24h\": 4.37185,\n" + "                    \"percent_change_7d\": -12.1352,\n" + "                    \"market_cap\": 113563929433.21645,\n" + "                    \"last_updated\": \"2018-08-09T21:56:28.000Z\"\n" + "                },\n" + "                \"EUR\": {\n" + "                    \"price\": 6666.66666666,\n" + "                    \"volume_24h\": 4314444687.5194,\n" + "                    \"percent_change_1h\": 0.988615,\n" + "                    \"percent_change_24h\": 4.37185,\n" + "                    \"percent_change_7d\": -12.1352,\n" + "                    \"market_cap\": 113563929433.21645,\n" + "                    \"last_updated\": \"2018-08-09T21:56:28.000Z\"\n" + "                }\n" + "            }\n" + "        }\n" + "    },\n" + "    \"status\": {\n" + "        \"timestamp\": \"2020-05-01T08:00:08.460Z\",\n" + "        \"error_code\": 0,\n" + "        \"error_message\": \"\",\n" + "        \"elapsed\": 10,\n" + "        \"credit_count\": 1\n" + "    }\n" + "}";

    String quote() {
        return STATIC_JSON_RESULT;
    }
}
