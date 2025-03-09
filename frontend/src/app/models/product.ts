export interface Product {
    id: number;
    name: string;
    description: string;
    price: number;
    stock: number;
    isAvailable: boolean;
    categoryName: string;
    imageUrls: { id: number; imageUrl: string }[];
}
