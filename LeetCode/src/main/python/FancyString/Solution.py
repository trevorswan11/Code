from itertools import groupby

class Solution:
    def makeFancyString(self, s: str) -> str:
        return ''.join(c*min(len([*p]),2) for c,p in groupby(s))