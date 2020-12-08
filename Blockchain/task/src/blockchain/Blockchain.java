package blockchain;

import java.util.LinkedList;
import java.util.List;

public class Blockchain {
    List<Block> list = new LinkedList<>();
    int n = 0;
    boolean ready = true;

    public synchronized Block getLast() {
        if (list.size() == 0) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    public synchronized void put(Block block) {
        if (ready) {
            Block previousBlock = getLast();
            if (previousBlock != null) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    builder.append("0");
                }
                String zeros = builder.toString();
                //System.out.println("block = " + block);
                //System.out.println("previousblock = " + previousBlock);
                if (block.previousBlock != null && previousBlock != null && block.previousBlock.hash.equals(previousBlock.hash) && block.hash.startsWith(zeros) &&
                        block.id == block.previousBlock.id + 1) {
                    list.add(block);
                }
            } else {
                list.add(block);
            }
        }
        ready = false;
    }
}
