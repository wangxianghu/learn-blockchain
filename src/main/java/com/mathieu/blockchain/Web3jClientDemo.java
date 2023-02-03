package com.mathieu.blockchain;

import com.alibaba.fastjson.JSONObject;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;

/**
 * @author wangxianghu
 */
public class Web3jClientDemo {
  public static void main(String[] args) throws IOException {
    String url = "https://bsc-mainnet.nodereal.io/v1/64a9df0874fb4a93b9d0a3849de012d3";

    // 第二个参数表示是否返回原始response数据，原始response中的数据为16进制
    Web3j web3j = Web3j.build(new HttpService(url, false));
    // 获取最新block id
    BigInteger latestBlockNum = web3j.ethBlockNumber().send().getBlockNumber();
    System.out.println(latestBlockNum);

    // 查询指定block 信息(这里用刚刚获取的blockId示范)
    // 第二个参数表示是否返回区块中的交易数据
    EthBlock.Block block = web3j.ethGetBlockByNumber(new DefaultBlockParameterNumber(latestBlockNum), true)
        .send()
        .getBlock();

    System.out.println(JSONObject.toJSONString(block));

    // 关闭客户端
    web3j.shutdown();
  }
}
