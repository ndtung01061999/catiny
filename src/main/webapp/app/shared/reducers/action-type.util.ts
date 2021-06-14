/**
 * Appends REQUEST async action type
 */

export const REQUEST = actionType => `${actionType}_PENDING`;

/**
 * Appends SUCCESS async action type
 */

export const SUCCESS = actionType => `${actionType}_FULFILLED`;

/**
 * Appends FAILURE async action type
 */

export const FAILURE = actionType => `${actionType}_REJECTED`;

/**
 * Appends WS_MESSAGE async action type
 */

export const WS_MESSAGE = actionType => `${actionType}_WS_MESSAGE`;

/**
 * Appends WS_SEND  async action type
 */

export const WS_SEND = actionType => `${actionType}_WS_SEND`;
