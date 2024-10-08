class Solution:
    def multiply_matrices(self, a, b):
        # Multiplies two 2x2 matrices
        return [[a[0][0] * b[0][0] + a[0][1] * b[1][0], a[0][0] * b[0][1] + a[0][1] * b[1][1]],
                [a[1][0] * b[0][0] + a[1][1] * b[1][0], a[1][0] * b[0][1] + a[1][1] * b[1][1]]]

    def power_matrix(self, m, n):
        # Exponentiates the matrix m to the power of n
        if n == 1:
            return m
        if n % 2 == 0:
            half_power = self.power_matrix(m, n // 2)
            return self.multiply_matrices(half_power, half_power)
        else:
            return self.multiply_matrices(m, self.power_matrix(m, n - 1))

    def fib_matrix(self, n):
        # Returns the nth Fibonacci number using matrix exponentiation
        if n <= 1:
            return n
        transformation_matrix = [[1, 1], [1, 0]]
        result_matrix = self.power_matrix(transformation_matrix, n - 1)
        return result_matrix[0][0]
    
    def fib_recursive(self, n):
        # Returns the nth Fibonacci number using recursion
        if n <= 1:
            return n
        return self.fib_recursive(n - 1) + self.fib_recursive(n - 2)
    
    def fib_iterative(self, n):
        # Returns the nth Fibonacci number using iteration
        if n <= 1:
            return n
        a = 0
        b = 1
        for i in range(2, n + 1):
            c = a + b
            a = b
            b = c
        return b
    
    def fib_binet_formula(self, n):
        # Returns the nth Fibonacci number using the Binet's formula
        return int((((1 + 5 ** 0.5) / 2) ** n - ((1 - 5 ** 0.5) / 2) ** n) / 5 ** 0.5)