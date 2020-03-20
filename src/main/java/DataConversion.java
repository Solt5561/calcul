public  class DataConversion {
    String[] romanNumbers = new String[] { "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
    int[] latinNumbers = new int[] { 1, 4, 5, 9, 10, 40, 50, 90, 100};

    public  String latinToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int times = 0;

        for (int i = latinNumbers.length - 1; i >= 0; i--) {
            times = num / latinNumbers[i];
            num %= latinNumbers[i];
            while (times > 0) {
                sb.append(romanNumbers[i]);
                times--;
            }
        }
        return sb.toString();
    }
}

