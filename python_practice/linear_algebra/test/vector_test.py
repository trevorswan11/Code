import os, sys
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))
import pytest
from src.vector import Vector

class TestVector:
    def test_init(self):
        v = Vector(1, 2, 3)
        assert v.components == [1, 2, 3]
    def test_sub(self):
        v1 = Vector(5, 7, 9)
        v2 = Vector(4, 5, 6)
        result = v1 - v2
        assert result.components == [1, 2, 3]
    def test_scalar_multiplication(self):
        v = Vector(1, 2, 3)
        result = v * 3
        assert result.components == [3, 6, 9]
    def test_dot_product(self):
        v1 = Vector(1, 2, 3)
        v2 = Vector(4, 5, 6)
        result = v1.dot(v2)
        assert result == 32
    def test_projection(self):
        v1 = Vector(3, 4)
        v2 = Vector(1, 1)
        result = v1.projection(v2)
        assert result.components == [3.5, 3.5]
    def test_projection_invalid_length(self):
        v1 = Vector(1, 2)
        v2 = Vector(1, 2, 3)
        with pytest.raises(ValueError, match="Vector objects must be of equal length."):
            v1.projection(v2)

if __name__ == "__main__":
    pytest.main()