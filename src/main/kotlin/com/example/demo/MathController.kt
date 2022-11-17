package com.example.demo

import com.example.demo.exceptions.UnsupportedMathOperationException
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.Math.pow

@RestController
class MathController {

    @RequestMapping(value = ["/sum/{numberOne}/{numberTwo}"])
    fun sum(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw UnsupportedMathOperationException("Please set a numeric value")
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    @RequestMapping(value = ["/sub/{numberOne}/{numberTwo}"])
    fun sub(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw UnsupportedMathOperationException("Please set a numeric value")
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }


    @RequestMapping(value = ["/mul/{numberOne}/{numberTwo}"])
    fun mul(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw UnsupportedMathOperationException("Please set a numeric value")
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    @RequestMapping(value = ["/div/{numberOne}/{numberTwo}"])
    fun div(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw UnsupportedMathOperationException("Please set a numeric value")
        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    @RequestMapping(value = ["/sqrt/{numberOne}"])
    fun sqrt(
        @PathVariable(value = "numberOne") numberOne: String?
    ): Double {
        if (!isNumeric(numberOne))
            throw UnsupportedMathOperationException("Please set a numeric value")
        return pow(convertToDouble(numberOne),0.5)
    }


    private fun convertToDouble(strNumber: String?): Double {
        if (strNumber.isNullOrBlank()) return 0.0

        val number = strNumber.replace(",".toRegex(), ".")
        return if (isNumeric(number)) number.toDouble() else 0.0
    }

    private fun isNumeric(strNumber: String?): Boolean {
        if (strNumber.isNullOrBlank()) return false;

        val number = strNumber.replace(",".toRegex(), ".")
        return number.matches("""[-+]?[0-9]*\.?[0-9]+""".toRegex());
    }
}