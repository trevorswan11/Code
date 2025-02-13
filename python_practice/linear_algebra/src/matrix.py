from math import *
from typing import Union

class Matrix:
    """An n x m-dimensional matrix with standard matrix operations.""" 
    def __valid_op(self, other: 'Matrix' = None, op: str = None):
        match op:
            case 'add', 'sub':
                if self.num_rows != other.num_rows or self.num_cols != other.num_cols: 
                    raise ValueError('Matrices must be of equal length for addition and subtraction.')
            case 'dot':
                if self.num_cols != other.num_rows:
                    raise ValueError('Number of columns in the first matrix must equal the number of rows in the second for dot products.')
            case 'det':
                if self.num_rows != self.num_cols:
                    raise ValueError('Determinant can only be calculated for square matrices')
    def __init__(self, *rows):
        self.rows = list(row for row in rows)
        self.num_rows = len(self.rows)
        self.num_cols = len(self.rows[0]) if self.rows else 0
    def __len__(self) -> tuple: return (self.num_rows, self.num_cols)
    def __repr__(self) -> str: return '\n'.join(['[' + ', '.join(map(str, row)) + ']' for row in self.rows])
    def __add__(self, other: 'Matrix') -> 'Matrix':
        self.__valid_op(other, 'add')
        return Matrix(*([self.rows[i][j] + other.rows[i][j] for j in range(self.num_cols)] for i in range(self.num_rows)))
    def __sub__(self, other: 'Matrix') -> 'Matrix':
        self.__valid_op(other, 'sub')
        return Matrix(*([self.rows[i][j] - other.rows[i][j] for j in range(self.num_cols)] for i in range(self.num_rows)))
    def __mul__(self, scalar: float) -> 'Matrix': return Matrix(*([scalar * element for element in row] for row in self.rows)) 
    def __getitem__(self, i: int, j: int = None) -> Union[list, float]: return self.rows[i] if not j else self.rows[i][j]
    def dot(self, other: 'Matrix') -> 'Matrix':
        self.__valid_op(other, 'dot')
        return Matrix(*(
            [sum(self.rows[i][k] * other.rows[k][j] for k in range(self.num_cols)) for j in range(other.num_cols)]
            for i in range(self.num_rows)))
    @property
    def T(self): return Matrix(*([self.rows[j][i] for j in range(self.num_rows)] for i in range(self.num_cols)))
    @property
    def det(self): 
        self.__valid_op(op = 'det')
        if self.num_rows == 1: return self.rows[0][0]
        elif self.num_rows == 2: return self.rows[0][0] * self.rows[1][1] - self.rows[0][1] * self.rows[1][0]
    def minor(self, row: int, col: int) -> 'Matrix':
        """Return the minor of the matrix after removing the specified row and column."""                                            
        return Matrix(*([self.rows[i][:col] + self.rows[i][col + 1:] for i in range(self.num_rows) if i != row]))
    
mat1 = Matrix([1,2], [3,4])
mat2 = Matrix([5,6], [7,8], [9,10])
print(mat2)
print('--')
print(mat2.T.minor(0,1))

