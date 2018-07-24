package com.mercadolibre.ingreso.dao;

import com.mercadolibre.ingreso.commons.Logs;
import com.mercadolibre.ingreso.entity.DayWeatherDTO;
import com.mercadolibre.ingreso.entity.WeatherStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

/**
 * @author Fabricio Cejas (fabrizzio.cejas.80@gmail.com)
 */
@Repository
public class WeatherDAO {

    @Autowired
    private Logs log;

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Find Weather using day number
     *
     * @param dayWeatherDTO
     * @return
     */
    public Optional<DayWeatherDTO> findWeatherByDay(DayWeatherDTO dayWeatherDTO) {

        StopWatch chronometer = new StopWatch();
        chronometer.start();

        String query = "SELECT id, day, weather FROM public.civ_day_weather WHERE day = ?";

        try {
            dayWeatherDTO = jdbcTemplate.queryForObject(query, new Object[]{dayWeatherDTO.getDay()}, (rs, rowNum) -> {
                return new DayWeatherDTO(rs.getLong("id"), rs.getInt("day"), rs.getString("weather"));
            });
        } catch (DataAccessException e) {
            log.exceptions().warn("[There is no data for day {}]", dayWeatherDTO.getDay());
            dayWeatherDTO.setWeather(WeatherStatus.ERROR);
        }

        chronometer.stop();

        log.dao().debug("[{}][recover day][{}][response time: {}ms]", dayWeatherDTO, chronometer.getTotalTimeSeconds());

        return Optional.of(dayWeatherDTO);
    }

    /**
     * Save a day in data Base
     *
     * @param dayWeatherDTO
     * @return
     */
    public Optional<DayWeatherDTO> saveDay(DayWeatherDTO dayWeatherDTO) {

        StopWatch chronometer = new StopWatch();
        chronometer.start();

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                PreparedStatement ps = con.prepareStatement("INSERT INTO public.civ_day_weather(day, weather, perimeter) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, dayWeatherDTO.getDay());
                ps.setString(2, dayWeatherDTO.getWeather().getValue());
                ps.setDouble(3, dayWeatherDTO.getPerimeter());

                return ps;
            }
        }, holder);

        Long id = Long.valueOf(holder.getKeys().get("id").toString());
        dayWeatherDTO.setId(id);

        chronometer.stop();

        log.dao().debug("[SAVE day][new id: {}][response time: {}ms]", id, chronometer.getTotalTimeSeconds());

        return Optional.of(dayWeatherDTO);
    }

    /**
     * Recover amount registers of day
     *
     * @return Integer
     */
    public Optional<Integer> getAmountRegisters() {

        StopWatch chronometer = new StopWatch();
        chronometer.start();

        String query = "SELECT COUNT(1) FROM public.civ_day_weather";

        Integer amountRegisters = jdbcTemplate.queryForObject(query, new Object[]{}, Integer.class);

        chronometer.stop();

        log.dao().debug("[recover amount registers: {}][response time: {}ms]", amountRegisters, chronometer.getTotalTimeSeconds());

        return Optional.of(amountRegisters);
    }
}
