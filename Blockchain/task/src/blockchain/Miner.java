package blockchain;

import java.time.LocalTime;
import java.util.Random;

public class Miner implements Runnable{
    int number;
    Blockchain blockchain;

    Miner(int number, Blockchain blockchain) {
        this.number = number;
        this.blockchain = blockchain;
    }

    public Block generateBlock(Block previousBlock, int n) {
        if (previousBlock == null) {
            Block block = new Block(1, previousBlock, this);
            creatingHash(block, n);
            return block;
        } else {
            Block block = new Block(previousBlock.id + 1, previousBlock, this);
            creatingHash(block, n);
            return block;
        }

    }

    public void creatingHash(Block block, int n) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append("0");
        }
        String previousHash;
        if (block.previousBlock == null) {
            previousHash = "";
        } else {
            previousHash = block.previousBlock.hash;
        }
        Random random = new Random();
        String dataToHash;
        int magic = random.nextInt(99999999);
        LocalTime begin = LocalTime.now();
        String hash;
        while (true) {
            dataToHash = previousHash
                    + Long.toString(block.timeStamp)
                    + block.id +
                    + magic;
            hash = StringUtil.applySha256(dataToHash);
            if (hash.startsWith(builder.toString())) {
                break;
            }
            magic = random.nextInt(99999999);
        }
        LocalTime end = LocalTime.now();
        block.secondsForCreation = end.toSecondOfDay() - begin.toSecondOfDay();
        block.hash = hash;
        block.magicNumber = magic;
    }

    public void process() {
        Block previousBlock = blockchain.getLast();
        Block block = generateBlock(previousBlock, blockchain.n);
        blockchain.put(block);
    }


    @Override
    public void run() {
        process();
    }
}
