import pandas as pd
from pathlib import Path

def modifySalaryColumn(employees: pd.DataFrame) -> pd.DataFrame:
    for idx, row in employees.iterrows():
        employees.loc[idx, "salary"] = row["salary"] * 2
    return employees