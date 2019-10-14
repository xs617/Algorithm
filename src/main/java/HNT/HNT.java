package HNT;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class HNT {
    List<Stack<Integer>> tower = new ArrayList<>();

    {
        for (int i = 0; i < 3; i++) {
            tower.add(new Stack<>());
        }
    }

    public static void main(String[] args) {
        HNT hnt = new HNT();
        hnt.buildHNT(4);
        hnt.moveTower(0, 1, 2, hnt.tower.get(0).size());
    }

    private void buildHNT(int layerNumber) {
        for (int i = 0; i < 3; i++) {
            tower.get(i).clear();
        }
        for (int i = layerNumber; i >= 1; i--) {
            tower.get(0).push(i);
        }
    }

    private void printHNT() {
        int maxHeight = 0;
        for (int i = 0; i < tower.size(); i++) {
            maxHeight = Math.max(maxHeight, tower.get(i).size());
        }
        for (int i = 0; i < maxHeight; i++) {
            for (int r = 0; r < tower.size(); r++) {
                int index = maxHeight - i - 1;
                if (tower.get(r).size() > index) {
                    System.out.print(tower.get(r).get(index));
                } else {
                    System.out.print(" ");
                }
                System.out.print("     ");
            }
            System.out.println();
        }
        System.out.println(" -------------");
    }

    private void moveTower(int start, int cache, int target, int moveNum) {
        //只要移动一个元素的时候直接移动
        if (moveNum == 1) {
            tower.get(target).push(tower.get(start).pop());
            printHNT();
            return;
        }
        //把start n-1的放到cache中，剩下一个直接移动
        moveTower(start, target, cache, moveNum - 1);
        tower.get(target).push(tower.get(start).pop());
        printHNT();
        //对cache剩下的n-1个重复上述操作
        moveTower(cache, start, target, moveNum - 1);

    }

}
