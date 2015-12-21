# AHV-validator
Simple Java validator for AHV (Swiss Social Security) Numbers
This class validates AHV codes, both formatted and unformatted. Final control digit is also checked.

## Accepted formats

nnn.nnnn.nnnn.nnn (like 756.1234.5678.97)

nnnnnnnnnnnnnn (like 7561234567897)


##How-to
If you need to validate a String as a AHV number just add the main class AHVNumberValidator to your classpath and call:
```
AHVNumberValidator.isValidAHVNumber(stringToCheck)
```
