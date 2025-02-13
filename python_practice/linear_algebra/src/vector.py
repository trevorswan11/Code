from math import sqrt
from numpy import cross as cross_prod

class Vector:
    """An n-dimensional vector with standard vector operations."""    
    def __valid_op(self, other: 'Vector') -> Exception:
        if len(self) != len(other): raise ValueError("Vector objects must be of equal length.")
    def __init__(self, *components): self.components = list(components)                             # the asterisk allows any number of inputs to be entered
    def __repr__(self) -> str: return f"Vector<{', '.join(map(str, self.components))}>"             # __asdf__ is operator overloading                                             
    def __len__(self) -> int: return len(self.components)
    def __add__(self, other: 'Vector') -> 'Vector':
        self.__valid_op(other)
        return Vector(*(a + b for a, b in zip(self.components, other.components)))
    def __sub__(self, other: 'Vector') -> 'Vector':
        self.__valid_op(other)
        return Vector(*(a - b for a, b in zip(self.components, other.components)))
    def __mul__(self, scalar: float) -> 'Vector': return Vector(*(scalar * a for a in self.components))
    def __getitem__(self, i: int) -> float: return self.components[i]
    def dot(self, other: 'Vector'):
        self.__valid_op(other)
        return sum(a * b for a, b in zip(self.components, other.components))
    def cross(self, other: 'Vector') -> 'Vector':
        self.__valid_op(other)
        if len(self) != 3: raise ValueError('Cross product is only defined for 3D vectors.')
        return Vector(*(cross_prod(self.components, other.components)))
    @property
    def mag(self) -> float: return sqrt(sum(a ** 2 for a in self.components))
    @property
    def norm(self) -> 'Vector': 
        if (mag := self.magnitude()) == 0: raise ValueError("Cannot normalize a zero vector.")
        return Vector(*(a / mag for a in self.components))
    def projection(self, other: 'Vector') -> 'Vector':
        self.__valid_op(other)
        if other.dot(other) == 0: raise ValueError('Cannot project onto zero vector.')
        return other * (self.dot(other) / other.dot(other))                                         # proj_b a = (a * b / b * a) * b