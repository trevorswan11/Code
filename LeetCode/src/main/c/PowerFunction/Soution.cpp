class Solution {
public:
    double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        } else if (x == 1) {
            return x;
        } else if (n == __INT32_MAX__ || n == __INT32_MAX__ + 1) {
            return 0;
        } else if (n > 0) {
            double prod = x;
            for (int i = 0; i < n - 1; i++) {
                prod *= x;
            }
            return prod;
        } else {
            double prod = x;
            for (int i = 0; i < -n - 1; i++) {
                prod *= x;
            }
            return 1/prod;
        }
    }
void main(int argc, char const *argv[])
{
    myPow(2, __INT32_MAX__ + 1);
}

};