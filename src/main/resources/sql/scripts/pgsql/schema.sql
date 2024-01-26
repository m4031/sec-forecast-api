create database stockforecast;

\connect stockforecast;

CREATE TABLE IF NOT EXISTS dbo.forecast_price
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1 MINVALUE 1),
    date integer NOT NULL,
    ticker character varying(10) NOT NULL,
	algo character varying(30) NOT NULL,
    country character varying(3) NOT NULL,
    currency character varying(3) NOT NULL,
    close_price double precision NOT NULL,
    forecast_prices double precision[] NOT NULL,
    market_trend character varying(10) NOT NULL,
    forecast_trend character varying(10) NOT NULL,
    market_sentiment character varying(10) NOT NULL,
    last_update_user character varying(20) NOT NULL,
    last_update_time date NOT NULL,
    CONSTRAINT forecast_price_pkey PRIMARY KEY (id),
	CONSTRAINT forecast_price_unq_key UNIQUE (date, ticker, algo)
);

CREATE TABLE dbo.forecast_dates
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1 MINVALUE 1),
    date integer NOT NULL,
    forecast_dates integer[] NOT NULL,
    last_update_user character varying(20) NOT NULL,
    last_update_time date NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT forecast_dates_unq_key UNIQUE (date)
);


CREATE TABLE dbo.contact_messages
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1 MINVALUE 1),
    date integer NOT NULL,
	contact_id character varying(50) NOT NULL,
	email character varying(100) NOT NULL,
	subject character varying(500) NOT NULL,
	message character varying(2000) NOT NULL,
    last_update_user character varying(20) NOT NULL,
    last_update_time date NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT contact_messages_unq_key UNIQUE (contact_id)
);

CREATE TABLE dbo.news_subscription
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1 MINVALUE 1),
	subscription_id character varying(50) NOT NULL,
	name character varying(50) NOT NULL,
	email character varying(100) NOT NULL,
    last_update_user character varying(20) NOT NULL,
    last_update_time date NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT news_subscription_unq_key UNIQUE (subscription_id)
);

CREATE TABLE dbo.closing_info (
  id bigint NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1 MINVALUE 1),
  date INT NOT NULL,
  ticker VARCHAR(10) NOT NULL,
  country VARCHAR(3) NOT NULL,
  currency VARCHAR(3) NOT NULL,
  price_open double PRECISION NOT NULL,
  price_high double precision NOT NULL,
  price_low double precision NOT NULL,
  price_close double precision NOT NULL,
  volume_high double precision NOT NULL,
  volume_low double precision NOT NULL,
  last_update_user VARCHAR(10) NOT NULL,
  last_update_time DATE NOT NULL
);