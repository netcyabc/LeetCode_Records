import java.util.Vector;

class Solution {
    public static String getHappyString(int n, int k) {
        if (n == 1) {
            return (k == 3) ? "c" : (k == 2 ? "b" : (k == 1 ? "a" : ""));
        }
        int layer = n, layerSize = 2;
        // layer means the l-th level
        int[] layers = new int[n + 1];
        layers[n] = 1;
        while (layer > 0 && k > layerSize) {
            //size of layer n-1
            layers[layer - 1] = layerSize;
            layer--;
            layerSize *= (layer == 1) ? 3 : 2;
        }
        if (layer == 0) return "";
        layers[layer - 1] = layerSize;
        int[] sequence = new int[n - layer + 1];
        int initial = layer;
//        int[] result = getNthSequence(layer + 1, layerSize / ((layer == 0) ? 3 : 2), n, k, original);
//        layer+=1;
        if (layer == 1) {
            // solve layer-1 issue
            if (layers[0] >= k && k > 2 * layers[1]) {
                sequence[0] = 3;
                k -= 2 * layers[1];
            } else if (2 * layers[1] >= k && k > layers[1]) {
                sequence[0] = 2;
                k -= layers[1];
            } else {
                sequence[0] = 1;
            }
            layer++;
        }
        while (layer <= n) {
            if (layers[layer - 1] >= k && k > layers[layer - 1 + 1]) {
                // this level matters
                sequence[layer - initial] = 2;
                k -= layers[layer - 1 + 1];
            } else if (k <= layers[layer - 1 + 1]) {
                // depends on next level
                sequence[layer - initial] = 1;
            }
            layer++;
        }
        // translate sequence into abc
        StringBuffer ans = new StringBuffer();
        char lastChr = 'c';
        boolean skip1stFlag = false;
        if (n > sequence.length) {
            // non-sense default value
            for (int i = 0; i < n - sequence.length; i++) {
                Vector<Character> chr = new Vector<>();
                chr.addElement('a');
                chr.addElement('b');
                chr.addElement('c');
                chr.removeElement(lastChr);
                lastChr = chr.elementAt(0);
                ans.append(lastChr);
            }
        } else {
            lastChr = (sequence[0] == 3) ? 'c' : (sequence[0] == 2 ? 'b' : 'a');
            ans.append(lastChr);
            skip1stFlag = true;
        }

        for (int each : sequence) {
            if (skip1stFlag) {
                skip1stFlag = false;
                continue;
            }
            Vector<Character> chr = new Vector<>();
            chr.addElement('a');
            chr.addElement('b');
            chr.addElement('c');
            chr.removeElement(lastChr);
            lastChr = each == 2 ? chr.elementAt(1) : chr.elementAt(0);
            ans.append(lastChr);
        }
        return ans.toString();
    }

}

public class getHappyString {
    public static void main(String[] args) {
        System.out.println(Solution.getHappyString(3, 12));
//        System.out.println(Solution.getHappyString(2, 4));
//        System.out.println(Solution.getHappyString(3, 9));
//        System.out.println(Solution.getHappyString(10, 100));
        // 2 2 1 1 1 2 1
        System.out.print("Run successful.");
    }
}
