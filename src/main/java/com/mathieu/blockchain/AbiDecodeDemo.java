package com.mathieu.blockchain;

import com.alibaba.fastjson.JSONObject;
import net.osslabz.evm.abi.decoder.AbiDecoder;
import net.osslabz.evm.abi.decoder.DecodedFunctionCall;

import java.io.IOException;

/**
 * 解析不同的input需要使用其对应的abi
 * abi查询网址 ： https://www.smartcontracttoolkit.com/abi
 * <p>
 * 一条input对应的api类型可以通过txn的token去查询tokenMeta获取，tokenMeta中的 tokenType就是abi类型
 * https://docs.nodereal.io/reference/nr_gettokenmeta#example
 * <p>
 * curl https://bsc-mainnet.nodereal.io/v1/your-api-key \
 * -X POST \
 * -H "Content-Type: application/json" \
 * -d '{"jsonrpc":"2.0","method":"nr_getTokenMeta","params":["0x8AC76a51cc950d9822D68b83fE1Ad97B32Cd580d"],"id": 0 }'
 * <p>
 * {
 *   "id": "0",
 *   "jsonrpc": "2.0",
 *   "result": {
 *     "name": "USD Coin",
 *     "symbol": "USDC",
 *     "decimails": 18,
 *     "tokenType": "erc20"
 *   }
 * }
 *
 * @author wangxianghu
 */
public class AbiDecodeDemo {
  public static void main(String[] args) throws IOException {
    AbiDecoder abiDecoder = new AbiDecoder("abiFiles/ERC20.json");
    String input = "0xa9059cbb0000000000000000000000006eba81f430ff5ab8daee861832c015701db87b2400000000000000000000000000000000000000000000001ffbe5120bc8780000";
    // 解析input
    DecodedFunctionCall decodedFunctionCall = abiDecoder.decodeFunctionCall(input);
    System.out.println(JSONObject.toJSONString(decodedFunctionCall.getParams()));
    // 结果
    // [{"name":"_to","type":"address","value":"0x6eba81f430ff5ab8daee861832c015701db87b24"},{"name":"_value","type":"uint256","value":590000000000000000000}]
  }
}
