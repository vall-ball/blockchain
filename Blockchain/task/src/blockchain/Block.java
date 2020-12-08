package blockchain;

import java.util.Date;

public class Block {
    int id;
    long timeStamp;
    String hash;
    Block previousBlock;
    int magicNumber;
    int secondsForCreation;
    Miner miner;

    Block(int id, Block previousBlock, Miner miner) {
        this.id = id;
        this.previousBlock = previousBlock;
        timeStamp = new Date().getTime();
        this.miner = miner;
    }

    @Override
    public String toString() {
        String previousHash;
        if (previousBlock == null) {
            previousHash = "0";
        } else {
            previousHash = previousBlock.hash;
        }
        return "Block:\n" +
                "Created by miner # " + miner.number + "\n" +
                "Id: " + id + "\n" +
                "Timestamp: " + timeStamp + "\n" +
                "Magic number: " + magicNumber + "\n" +
                "Hash of the previous block:\n" + previousHash + "\n" +
                "Hash of the block:\n" + hash + "\n" +
                "Block was generating for " + secondsForCreation + " seconds";
    }
}

