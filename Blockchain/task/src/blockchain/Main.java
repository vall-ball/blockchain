package blockchain;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Blockchain blockchain = new Blockchain();

        List<Miner> miners = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            miners.add(new Miner(i + 1, blockchain));
        }

        int count = 0;

        while (count != 5) {
            for (int i = 0; i < 10; i++) {
                (new Thread(miners.get(i))).start();
            }
            Thread.sleep(200);
            Block lastBlock = blockchain.getLast();
            System.out.println(lastBlock);
            if (lastBlock.secondsForCreation >= 60) {
                System.out.println("N was decreased by 1");
                blockchain.n--;
            } else if (lastBlock.secondsForCreation >= 15) {
                System.out.println("N stays the same");
            } else {
                System.out.println("N was increased to " + (blockchain.n + 1));
                blockchain.n++;
            }
            count++;
            System.out.println();
            blockchain.ready = true;
        }
        System.out.println(blockchain.list.size());

    }
}
