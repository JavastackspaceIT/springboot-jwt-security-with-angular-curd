import { User } from './user';

export class ApiResponse {
    status: number;
    message: string;
    result: User[];
}

