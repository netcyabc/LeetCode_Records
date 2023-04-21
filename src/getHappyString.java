import java.util.Vector;

class Solution {
    public static String getHappyString(int n, int k) {
        int layer = n, layerSize = 2;
        // layer means the l-th level
        int[] layers = new int[n + 1];
        layers[n] = 1;
        while (layer >= 0 && k > layerSize) {
            //size of layer n-1
            layers[layer - 1] = layerSize;
            layer--;
            layerSize *= (layer == 1) ? 3 : 2;
        }
        layers[layer - 1] = layerSize;
        if (k > layerSize) return "";
        int[] sequence = new int[n - layer + 1];
        int initial = layer;
//        int[] result = getNthSequence(layer + 1, layerSize / ((layer == 0) ? 3 : 2), n, k, original);
//        layer+=1;
        if (layer == 1) {
            // solve layer-1 issue
            if (layers[0] > k && k > 2 * layers[1]) {
                sequence[0] = 3;
                k -= 2 * layers[1];
            } else if (2 * layers[1] > k && k > layers[1]) {
                sequence[0] = 2;
                k -= layers[1];
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
        char lastChr ='c';
        if(n> sequence.length){
            // non-sense default value
            for (int i = 0; i < n-sequence.length; i++) {
                Vector<Character> chr = new Vector<>();
                chr.addElement('a');
                chr.addElement('b');
                chr.addElement('c');
                chr.removeElement(lastChr);
                lastChr = chr.elementAt(0);
                ans.append(lastChr);
            }
        }else
            lastChr = (sequence[0] == 3) ? 'c' : (sequence[0] == 2 ? 'b' : 'a');
        boolean flag = true;
//        char
//        ans.append(lastChr);
        for (int each : sequence) {
//            if (flag) {
//                flag = false;
//                continue;
//            }
            Vector<Character> chr = new Vector<>();
            chr.addElement('a');
            chr.addElement('b');
            chr.addElement('c');
            chr.removeElement(lastChr);
            lastChr = each == 2 ? chr.elementAt(1) : chr.elementAt(0);
            ans.append(lastChr);
        }
        return "";
    }

//    public static int[] getNthSequence(int layer, int layerSize, int n, int k, int[] upper) {
//        if (layer > n) return upper;
//        int develSize = layerSize / 2;
//        if (layerSize > k && k > develSize) {
//            // at this layer
//        } else if (develSize > k) {
//        }
//        return new int[1];
//    }
}

public class getHappyString {
    public static void main(String[] args) {
//        System.out.println(Solution.getHappyString(3, 9));
        System.out.println(Solution.getHappyString(10, 100));
        // 2 2 1 1 1 2 1
        System.out.print("Run successful.");
    }
}