CREATE TABLE IF NOT EXISTS  agent_requested_invoices_state (
    scac character varying(4) COLLATE pg_catalog."default" NOT NULL,
    agent_customer_code character varying(20) COLLATE pg_catalog."default" NOT NULL,
    customer_code character varying(20) COLLATE pg_catalog."default" NOT NULL,
    invoice_number character varying(15) COLLATE pg_catalog."default" NOT NULL,
    bl_number character varying(10) COLLATE pg_catalog."default" NOT NULL,
    requested_date TIMESTAMP WITH TIME ZONE,
    status character varying(20) COLLATE pg_catalog."default" NOT NULL,
    requester_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    updated_by character varying(50) COLLATE pg_catalog."default",
    created_by character varying(50) COLLATE pg_catalog."default",
    created_date timestamp without time zone default CURRENT_TIMESTAMP not null,
    updated_date timestamp without time zone default CURRENT_TIMESTAMP not null,
    CONSTRAINT pk_agent_requested_invoices PRIMARY KEY (agent_customer_code, scac, invoice_number)
    );