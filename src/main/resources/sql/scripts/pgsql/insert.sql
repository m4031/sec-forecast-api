------------------- INSERT DATA INTO forecast_price ----------------------
INSERT INTO dbo.forecast_price (date, ticker, algo, country, currency, close_price, forecast_prices, market_trend,
forecast_trend, market_sentiment, last_update_user, last_update_time) VALUES (20231027, 'AAPL', 'LSTM', 'USA', 'USD',
 101.22, ARRAY[101.1234, 102.4567, 100.123456, 103.123, 102.123, 104.123, 105.123], 'UP', 'UP', 'UP', 'Sam', CURRENT_DATE);

INSERT INTO dbo.forecast_price (date, ticker, algo , country, currency, close_price, forecast_prices, market_trend,
forecast_trend, market_sentiment, last_update_user, last_update_time) VALUES (20231027, 'TSLA', 'LSTM', 'USA', 'USD',
 201.22, ARRAY[201.1234, 202.4567, 200.123456, 203.123, 202.123, 204.123, 205.123], 'UP', 'UP', 'UP', 'Sam', CURRENT_DATE);

INSERT INTO dbo.forecast_price (date, ticker, algo , country, currency, close_price, forecast_prices, market_trend,
forecast_trend, market_sentiment, last_update_user, last_update_time) VALUES (20231027, 'NVIDIA', 'LSTM', 'USA', 'USD',
 401.22, ARRAY[401.1234, 402.4567, 400.123456, 403.123, 402.123, 404.123, 405.123], 'UP', 'UP', 'UP', 'Sam', CURRENT_DATE);

INSERT INTO dbo.forecast_price (date, ticker, algo , country, currency, close_price, forecast_prices, market_trend,
forecast_trend, market_sentiment, last_update_user, last_update_time) VALUES (20231027, 'GOOGL', 'LSTM', 'USA', 'USD',
 122.22, ARRAY[121.1234, 120.4567, 119.123456, 117.123, 115.123, 112.123, 111.123], 'DOWN', 'DOWN', 'DOWN', 'Sam', CURRENT_DATE);

INSERT INTO dbo.forecast_price (date, ticker, algo , country, currency, close_price, forecast_prices, market_trend,
forecast_trend, market_sentiment, last_update_user, last_update_time) VALUES (20231027, 'MSFT', 'LSTM', 'USA', 'USD',
 329.91, ARRAY[330.1234, 331.4567, 332.123456, 333.123, 335.123, 340.123, 345.123], 'UP', 'UP', 'DOWN', 'Sam', CURRENT_DATE);

INSERT INTO dbo.forecast_price (date, ticker, algo, country, currency, close_price, forecast_prices, market_trend,
forecast_trend, market_sentiment, last_update_user, last_update_time) VALUES (20231026, 'AAPL', 'LSTM', 'USA', 'USD',
 101.22, ARRAY[102.1234, 103.4567, 101.123456, 104.123, 102.123, 103.123, 105.123], 'UP', 'UP', 'UP', 'Sam', CURRENT_DATE);

INSERT INTO dbo.forecast_price (date, ticker, algo , country, currency, close_price, forecast_prices, market_trend,
forecast_trend, market_sentiment, last_update_user, last_update_time) VALUES (20231026, 'TSLA', 'LSTM', 'USA', 'USD',
 201.22, ARRAY[202.1234, 203.4567, 205.123456, 207.123, 202.123, 204.123, 208.123], 'UP', 'UP', 'UP', 'Sam', CURRENT_DATE);

INSERT INTO dbo.forecast_price (date, ticker, algo , country, currency, close_price, forecast_prices, market_trend,
forecast_trend, market_sentiment, last_update_user, last_update_time) VALUES (20231026, 'NVIDIA', 'LSTM', 'USA', 'USD',
 401.22, ARRAY[405.1234, 403.4567, 406.123456, 409.123, 407.123, 408.123, 405.123], 'UP', 'UP', 'UP', 'Sam', CURRENT_DATE);

INSERT INTO dbo.forecast_price (date, ticker, algo , country, currency, close_price, forecast_prices, market_trend,
forecast_trend, market_sentiment, last_update_user, last_update_time) VALUES (20231026, 'GOOGL', 'LSTM', 'USA', 'USD',
 122.22, ARRAY[123.1234, 122.4567, 115.123456, 119.123, 125.123, 120.123, 121.123], 'DOWN', 'DOWN', 'DOWN', 'Sam', CURRENT_DATE);

INSERT INTO dbo.forecast_price (date, ticker, algo , country, currency, close_price, forecast_prices, market_trend,
forecast_trend, market_sentiment, last_update_user, last_update_time) VALUES (20231026, 'MSFT', 'LSTM', 'USA', 'USD',
 329.91, ARRAY[335.1234, 337.4567, 332.123456, 334.123, 336.123, 340.123, 342.123], 'UP', 'UP', 'DOWN', 'Sam', CURRENT_DATE);

------------------- INSERT DATA INTO forecast_dates ----------------------
INSERT INTO dbo.forecast_dates (date, forecast_dates, last_update_user, last_update_time) VALUES
(20231027, ARRAY[20231029, 20231030, 20231031, 20231101, 20231102, 20231103, 20231104], 'Sam', CURRENT_DATE);

INSERT INTO dbo.forecast_dates (date, forecast_dates, last_update_user, last_update_time) VALUES
(20231026, ARRAY[20231027, 20231028, 20231029, 20231030, 20231031, 20231101, 20231102], 'Sam', CURRENT_DATE);

------------------- INSERT DATA INTO closing_info ----------------------
INSERT INTO dbo.closing_info (date, ticker, country, currency, price_open, price_high, price_low, price_close, volume_high,
 volume_low, last_update_user, last_update_time) VALUES (20231027, 'AAPL', 'USA', 'USD', 101.1234, 107.5, 102.5,
  104.5, 50000000, 30000000, 'Sam', CURRENT_DATE);

INSERT INTO dbo.closing_info (date, ticker, country, currency, price_open, price_high, price_low, price_close, volume_high,
 volume_low, last_update_user, last_update_time) VALUES (20231027, 'TSLA', 'USA', 'USD', 203.1234, 220.5, 206.5,
  208.5, 80000000, 50000000, 'Sam', CURRENT_DATE);

INSERT INTO dbo.closing_info (date, ticker, country, currency, price_open, price_high, price_low, price_close, volume_high,
 volume_low, last_update_user, last_update_time) VALUES (20231027, 'NVIDIA', 'USA', 'USD', 402.1234, 475.5, 435.5,
  445.5, 90000000, 60000000, 'Sam', CURRENT_DATE);

INSERT INTO dbo.closing_info (date, ticker, country, currency, price_open, price_high, price_low, price_close, volume_high,
 volume_low, last_update_user, last_update_time) VALUES (20231027, 'GOOGL', 'USA', 'USD', 122.1234, 125.5, 112.5,
  115.5, 40000000, 20000000, 'Sam', CURRENT_DATE);

INSERT INTO dbo.closing_info (date, ticker, country, currency, price_open, price_high, price_low, price_close, volume_high,
 volume_low, last_update_user, last_update_time) VALUES (20231027, 'MSFT', 'USA', 'USD', 330.1234, 350.5, 319.5,
  319.5, 50000000, 40000000, 'Sam', CURRENT_DATE);

INSERT INTO dbo.closing_info (date, ticker, country, currency, price_open, price_high, price_low, price_close, volume_high,
 volume_low, last_update_user, last_update_time) VALUES (20231026, 'AAPL', 'USA', 'USD', 105.1234, 109.5, 100.5,
  101.1234, 40000000, 30000000, 'Sam', CURRENT_DATE);

INSERT INTO dbo.closing_info (date, ticker, country, currency, price_open, price_high, price_low, price_close, volume_high,
 volume_low, last_update_user, last_update_time) VALUES (20231026, 'TSLA', 'USA', 'USD', 201.1234, 225.5, 190.5,
  208.5, 60000000, 40000000, 'Sam', CURRENT_DATE);

INSERT INTO dbo.closing_info (date, ticker, country, currency, price_open, price_high, price_low, price_close, volume_high,
 volume_low, last_update_user, last_update_time) VALUES (20231026, 'NVIDIA', 'USA', 'USD', 405.1234, 450.5, 390.5,
  401.1234, 80000000, 50000000, 'Sam', CURRENT_DATE);

INSERT INTO dbo.closing_info (date, ticker, country, currency, price_open, price_high, price_low, price_close, volume_high,
 volume_low, last_update_user, last_update_time) VALUES (20231026, 'GOOGL', 'USA', 'USD', 122.1234, 125.5, 112.5,
  121.1234, 30000000, 20000000, 'Sam', CURRENT_DATE);

INSERT INTO dbo.closing_info (date, ticker, country, currency, price_open, price_high, price_low, price_close, volume_high,
 volume_low, last_update_user, last_update_time) VALUES (20231026, 'MSFT', 'USA', 'USD', 320.1234, 340.5, 315.5,
  330.1234, 40000000, 30000000, 'Sam', CURRENT_DATE);
