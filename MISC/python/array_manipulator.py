class ArrayManipulator:
    def vertical_flip(self, arr):
        return [row[::-1] for row in arr]

    def horizontal_flip(self, arr):
        return arr[::-1]

    def transpose(self, arr):
        return [list(row) for row in zip(*arr)]

import unittest

class TestArrayManipulator(unittest.TestCase):
    def setUp(self):
        self.arr_manipulator = ArrayManipulator()
        self.arr = [
            [1, 2, 3],
            [4, 5, 6],
            [7, 8, 9]
        ]
        self.expected_vertical_flip = [
            [3, 2, 1],
            [6, 5, 4],
            [9, 8, 7]
        ]
        self.expected_horizontal_flip = [
            [7, 8, 9],
            [4, 5, 6],
            [1, 2, 3]
        ]
        self.expected_transpose = [
            [1, 4, 7],
            [2, 5, 8],
            [3, 6, 9]
        ]

    def test_vertical_flip(self):
        result = self.arr_manipulator.vertical_flip(self.arr)
        self.assertEqual(result, self.expected_vertical_flip)

    def test_horizontal_flip(self):
        result = self.arr_manipulator.horizontal_flip(self.arr)
        self.assertEqual(result, self.expected_horizontal_flip)

    def test_transpose(self):
        result = self.arr_manipulator.transpose(self.arr)
        self.assertEqual(result, self.expected_transpose)

if __name__ == '__main__':
    unittest.main()