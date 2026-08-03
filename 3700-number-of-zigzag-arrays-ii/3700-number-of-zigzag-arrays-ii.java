class Solution {
    static final int MOD = 1_000_000_007;
    int m;

    public int zigZagArrays(int n, int l, int r) {
        m = r - l + 1;

        // L[v][u] = 1 if u < v,  R[v][u] = 1 if u > v   (values indexed 0..m-1)
        long[][] L = new long[m][m];
        long[][] R = new long[m][m];
        for (int v = 0; v < m; v++) {
            for (int u = 0; u < m; u++) {
                if (u < v) L[v][u] = 1;
                if (u > v) R[v][u] = 1;
            }
        }

        long[] U2 = new long[m]; // U2[v] = number of u < v  = v
        long[] D2 = new long[m]; // D2[v] = number of u > v  = m-1-v
        for (int v = 0; v < m; v++) {
            U2[v] = v;
            D2[v] = m - 1 - v;
        }

        if (n == 2) {
            long ans = 0;
            for (int v = 0; v < m; v++) ans = (ans + U2[v] + D2[v]) % MOD;
            return (int) ans;
        }

        long[] U3 = matVec(L, D2);
        long[] D3 = matVec(R, U2);

        long[][] M = matMul(L, R);   // drives the U-chain, two steps at a time
        long[][] Mp = matMul(R, L);  // drives the D-chain, two steps at a time

        long[] Un, Dn;
        if (n % 2 == 0) {
            int k = (n - 2) / 2;
            long[][] Mk = matPow(M, k);
            long[][] Mpk = matPow(Mp, k);
            Un = matVec(Mk, U2);
            Dn = matVec(Mpk, D2);
        } else {
            int k = (n - 3) / 2;
            long[][] Mk = matPow(M, k);
            long[][] Mpk = matPow(Mp, k);
            Un = matVec(Mk, U3);
            Dn = matVec(Mpk, D3);
        }

        long ans = 0;
        for (int v = 0; v < m; v++) ans = (ans + Un[v] + Dn[v]) % MOD;
        return (int) ans;
    }

    private long[][] matMul(long[][] A, long[][] B) {
        long[][] C = new long[m][m];
        for (int i = 0; i < m; i++) {
            for (int k = 0; k < m; k++) {
                long a = A[i][k];
                if (a == 0) continue;
                long[] Bk = B[k];
                long[] Ci = C[i];
                for (int j = 0; j < m; j++) {
                    Ci[j] = (Ci[j] + a * Bk[j]) % MOD;
                }
            }
        }
        return C;
    }

    private long[] matVec(long[][] A, long[] v) {
        long[] res = new long[m];
        for (int i = 0; i < m; i++) {
            long s = 0;
            for (int j = 0; j < m; j++) {
                s += A[i][j] * v[j] % MOD;
            }
            res[i] = s % MOD;
        }
        return res;
    }

    private long[][] matPow(long[][] A, int k) {
        long[][] result = identity();
        long[][] base = A;
        while (k > 0) {
            if ((k & 1) == 1) {
                result = matMul(result, base);
            }
            base = matMul(base, base);
            k >>= 1;
        }
        return result;
    }

    private long[][] identity() {
        long[][] I = new long[m][m];
        for (int i = 0; i < m; i++) I[i][i] = 1;
        return I;
    }
}