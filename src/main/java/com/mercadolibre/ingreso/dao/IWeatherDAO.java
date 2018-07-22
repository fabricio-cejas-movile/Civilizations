package com.mercadolibre.ingreso.dao;

import com.mercadolibre.ingreso.entity.DayWeatherDTO;

import java.util.Optional;

/**
 * @author Fabricio Cejas (fabrizzio.cejas.80@gmail.com)
 */
public interface IWeatherDAO {

    /**
     * Find Weather using day number
     *
     * @param dayWeatherDTO
     * @return
     */
    Optional<DayWeatherDTO> findWeatherByDay(DayWeatherDTO dayWeatherDTO);

    /**
     * Save a day in data Base
     *
     * @param dayWeatherDTO
     * @return
     */
    Optional<DayWeatherDTO> saveDay(DayWeatherDTO dayWeatherDTO);

    /**
     * Recover amount registers of day
     *
     * @return Integer
     */
    Optional<Integer> getAmountRegisters();
}
